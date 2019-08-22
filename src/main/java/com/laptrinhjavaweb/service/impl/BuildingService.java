package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuildingService implements IBuildingService{
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingConverter buildingConverter;

	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Override
	public BuildingDTO save(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(newBuilding);
		buildingEntity.setCreatedDate(new Date());
		buildingEntity.setCreatedBy("");
		buildingEntity.setType(StringUtils.join(newBuilding.getBuildingTypes(), ","));
		
		List<RentAreaEntity> areas = new ArrayList<>();
		//save rentarea
		if(newBuilding.getRentArea().length() > 0) {
			for (String item : newBuilding.getRentArea().split(",")) {
				RentAreaEntity rentArea = new RentAreaEntity();
				rentArea.setValue(Integer.parseInt(item));
				rentArea.setBuilding(buildingEntity);
				areas.add(rentArea);
			}
			buildingEntity.setAreas(areas);
		}
		buildingEntity = buildingRepository.save(buildingEntity);
		return buildingConverter.convertToDTO(buildingEntity);
	}

	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageable pageable) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder, pageable);
		List<BuildingDTO> results = buildingEntities.stream()
				.map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
		return results;
	}

	@Override
	public int count(BuildingSearchBuilder builder) {
		return buildingRepository.count(builder).intValue();
	}

    @Override
    public BuildingDTO findById(long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        return buildingConverter.convertToDTO(buildingEntity);
    }

	@Override
	public void update(BuildingDTO updateBuilding) {
		//43
	}

	@Override
	@Transactional
	public BuildingDTO save(Long id, BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		if(id != null){
			//update
			BuildingEntity oldBuilding = buildingRepository.findById(buildingDTO.getId()).get();
			buildingEntity = buildingConverter.convertToEntity(buildingDTO);
			buildingEntity.setCreatedBy(oldBuilding.getCreatedBy());
			buildingEntity.setCreatedDate(oldBuilding.getCreatedDate());
			rentAreaRepository.deleteByBuildingId(buildingDTO.getId());
		}else{
			//save new
			buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		}
		buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
		List<RentAreaEntity> areas = new ArrayList<>();
		if(buildingDTO.getRentArea().length() > 0){
			for(String item : buildingDTO.getRentArea().split(",")){
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(Integer.parseInt(item));
				rentAreaEntity.setBuilding(buildingEntity);
				areas.add(rentAreaEntity);
			}
			buildingEntity.setAreas(areas);
		}
		buildingEntity = buildingRepository.save(buildingEntity);
		return buildingConverter.convertToDTO(buildingEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(Long id : ids){
			//remove rentarea
			rentAreaRepository.deleteByBuildingId(id);
			//remove assignment
			BuildingEntity buildingEntity = buildingRepository.findById(id).get();
			for(UserEntity user : buildingEntity.getStaffs()){
				user.getBuildings().remove(buildingEntity);
			}
			buildingRepository.deleteById(id);
		}
	}
}
