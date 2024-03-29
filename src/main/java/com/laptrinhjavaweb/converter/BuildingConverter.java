package com.laptrinhjavaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;

@Component
public class BuildingConverter {
	
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		
		List<String> areas = buildingEntity.getAreas().stream()
								.map(RentAreaEntity::getValue)
								.map(item -> item.toString())
								.collect(Collectors.toList());
		
		if(areas.size() > 0) {
			result.setRentArea(StringUtils.join(areas, ","));
		}
		if(StringUtils.isNotBlank(buildingEntity.getType())) {
			result.setBuildingTypes(buildingEntity.getType().split(","));
		}	
		return result;
	}

	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
		if(StringUtils.isNotBlank(buildingDTO.getNumberOfBasement())) {
			result.setNumberOfBasement(Integer.parseInt(buildingDTO.getNumberOfBasement()));
		}
		if(StringUtils.isNotBlank(buildingDTO.getBuildingArea())){
			result.setBuildingArea(Integer.parseInt(buildingDTO.getBuildingArea()));	
		}	
		return result;
	}
}
