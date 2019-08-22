package com.laptrinhjavaweb.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.spi.ExecutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.laptrinhjavaweb.api.output.building.TotalItem;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BuildingAPI {

	@Autowired
	private IBuildingService buildingService; 
	
	@PostMapping(value = {"/api/building"})
	public BuildingDTO saveBuilding(@RequestBody BuildingDTO buildingDTO) {
		return buildingService.save(buildingDTO);
	}
	
	@GetMapping(value = {"/api/building"})
	public List<BuildingDTO> findAll(@RequestParam Map<String, Object> buildingQuery) {
		BuildingSearchBuilder builder = initBuildingBuider(buildingQuery);

		Pageable pageable = PageRequest.of(Integer.parseInt(buildingQuery.get("page").toString()) - 1,
				Integer.parseInt(buildingQuery.get("maxPageItem").toString()));

		/*Pageable pageable = PageRequest.of(Integer.valueOf((String)buildingQuery.get("page")) - 1),
											Integer.valueOf((String)buildingQuery.get("maxPageItem")));*/

		/*Integer page = Integer.parseInt(buildingQuery.get("page").toString());
		Integer maxPageItem = Integer.parseInt(buildingQuery.get("maxPageItem").toString());
		Pageable pageable = PageRequest.of(page - 1, maxPageItem);*/
		return buildingService.findAll(builder, pageable);
	}

	private BuildingSearchBuilder initBuildingBuider(Map<String, Object> buildingQuery) {
		String[] buildingTypes = new String[]{};
		if(StringUtils.isNotBlank((String) buildingQuery.get("buildingTypes"))){
			buildingTypes = ((String) buildingQuery.get("buildingTypes")).split(",");
		}
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName((String) buildingQuery.get("name"))
				.setNumberOfBasement((String) buildingQuery.get("numberOfBasement"))
				.setBuildingArea((String) buildingQuery.get("buildingArea"))
				.setDistrict((String) buildingQuery.get("district"))
				.setWard((String) buildingQuery.get("ward"))
				.setStreet((String) buildingQuery.get("street"))
				.setAreaRentFrom((String) buildingQuery.get("areaRentFrom"))
				.setAreaRentTo((String) buildingQuery.get("areaRentTo"))
				.setCostRentFrom((String) buildingQuery.get("costRentFrom"))
				.setCostRentTo((String) buildingQuery.get("costRentTo"))
				.setBuildingTypes(buildingTypes)
				.build();
		return builder;
	}

	@GetMapping(value = {"/api/building/total"})
	public TotalItem getTotalItem(@RequestParam Map<String, Object> buildingQuery) {
		BuildingSearchBuilder builder = initBuildingBuider(buildingQuery);
		return new TotalItem(buildingService.count(builder));
	}

	@GetMapping(value = {"/api/{id}/building"})
	public BuildingDTO findById(@PathVariable("id") long id) {
		return buildingService.findById(id);
	}

	@DeleteMapping(value = {"/api/building"})
	public void delete(@RequestBody long[] ids){
		buildingService.delete(ids);
	}

	@PutMapping(value = {"/api/building"})
	public void updateBuilding(@RequestBody BuildingDTO buildingDTO){
		buildingService.save(buildingDTO.getId(), buildingDTO);
	}
}
