package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@ApiModel(value = "actoresCorto", description = "Versión corta de actores")
public interface ActorShort {
	
	@ApiModelProperty(value = "Identificador de actor", required = true, accessMode = AccessMode.READ_ONLY)
	int getActorId();
	
	@ApiModelProperty(value = "Nombre completo del actor", required = true)
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombreCompleto();
}
