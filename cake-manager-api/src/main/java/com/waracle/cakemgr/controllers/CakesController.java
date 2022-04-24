package com.waracle.cakemgr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.services.CakeService;

import lombok.extern.slf4j.Slf4j;
/**
 * Rest Controller for the Cakes
 * Provides the API for Get Cakes and Add new Cake.
 * @author sandeepk1989
 *
 */
@RestController
@RequestMapping("/")
@Slf4j
public class CakesController {

	@Autowired
	private CakeService cakeService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<CakeDto>> getCakes() {
		try {
			List<CakeDto> cakes = cakeService.getAllCakes();
			return ResponseEntity.ok(cakes);
		} catch (NotFoundException e) {
			log.error("Error occured while getting the cakes: ", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/cakes")
	public ResponseEntity<byte[]> getCakesJson() throws Exception {
		try {
			byte[] data = cakeService.getCakesJson();
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=cakes.json")
					.contentType(MediaType.APPLICATION_JSON).contentLength(data.length).body(data);
		} catch (JsonProcessingException e) {
			log.error("Error while parsing Cakes Data to JSON", e);
			throw e;
		} catch (NotFoundException e) {
			log.error("Error occured while getting the cakes: ", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cakes")
	public ResponseEntity<Boolean> addCake(@RequestBody CakeDto cakeDto) {
		try {
			cakeService.addCake(cakeDto);
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error occured while getting the cakes: ", e);
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
