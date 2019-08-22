package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
	BuildingDTO save (BuildingDTO newBuilding);
	List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageable pageable);
	int count(BuildingSearchBuilder builder);
	BuildingDTO findById(long id);
	void delete(long[] ids);
	void update(BuildingDTO updateBuilding);
	BuildingDTO save(Long id, BuildingDTO buildingDTO);
}
