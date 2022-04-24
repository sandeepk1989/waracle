package com.waracle.cakemgr.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repositories.CakeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Cake Service class Interact with the Database repository to get the cakes
 * result and operations
 * 
 * @author sandeepk1989
 *
 */
@Service
@Slf4j
public class CakeService {

	@Autowired
	private CakeRepository cakeRepository;

	@Autowired
	private ObjectMapper objectMapper;

	public List<CakeDto> getAllCakes() throws NotFoundException {
		List<CakeEntity> cakeEntities = cakeRepository.findAll();
		if (cakeEntities == null || cakeEntities.size() == 0) {
			throw new EntityNotFoundException("No cakes found in the Database. ");
		}
		return cakeEntities.stream().map(
				cakeEntity -> new CakeDto(cakeEntity.getTitle(), cakeEntity.getDescription(), cakeEntity.getImage()))
				.collect(Collectors.toList());
	}

	public byte[] getCakesJson() throws NotFoundException, JsonProcessingException {
		List<CakeEntity> cakeEntities = cakeRepository.findAll();
		if (cakeEntities == null || cakeEntities.size() == 0) {
			throw new EntityNotFoundException("No cakes found in the Database. ");
		}
		return objectMapper.writeValueAsBytes(cakeEntities);
	}

	@Transactional
	public void addCake(CakeDto cakeDto) throws Exception {
		try {

			CakeEntity cakeEntity = new CakeEntity();
			cakeEntity.setTitle(cakeDto.getTitle()).setDescription(cakeDto.getDescription())
					.setImage(cakeDto.getImageUrl());
			CakeEntity createdCake = cakeRepository.save(cakeEntity);
			if (createdCake == null) {
				throw new Exception("Cake not created");
			}
		} catch (Exception e) {
			log.error("Error while saving the cake", e);
			throw e;
		}
	}

	@Transactional
	public void saveAll(List<CakeDto> users) {
		try {
			List<CakeEntity> cakeEntities = users.stream().map(cake -> {
				CakeEntity cakeEntity = new CakeEntity();
				cakeEntity.setTitle(cake.getTitle());
				cakeEntity.setDescription(cake.getDescription());
				cakeEntity.setImage(cake.getImageUrl());
				return cakeEntity;
			}).collect(Collectors.toList());

			cakeRepository.saveAll(cakeEntities);
		} catch (Exception e) {
			log.error("Error while saving the cake", e);
			throw e;
		}
	}
}
