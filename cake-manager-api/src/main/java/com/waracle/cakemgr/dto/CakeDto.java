package com.waracle.cakemgr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CakeDto {

	@JsonProperty("title")
	private String title;

	@JsonProperty("desc")
	private String description;

	@JsonProperty("image")
	private String imageUrl;

}
