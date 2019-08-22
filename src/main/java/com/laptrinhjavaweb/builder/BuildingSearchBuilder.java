package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {

	private String name;
	private String ward;
	private String street;
	private String numberOfBasement;
	private String buildingArea;
	private String district;	
	private String costRentFrom;
	private String costRentTo;
	private String areaRentFrom;
	private String areaRentTo;
	private String[] buildingTypes = new String[]{};

	public String getName() {
		return name;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}
	
	public String getNumberOfBasement() {
		return numberOfBasement;
	}
	
	public String getBuildingArea() {
		return buildingArea;
	}
	
	public String getDistrict() {
		return district;
	}

	public String getCostRentFrom() {
		return costRentFrom;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	public String getAreaRentFrom() {
		return areaRentFrom;
	}

	public String getAreaRentTo() {
		return areaRentTo;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	

	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.buildingArea = builder.buildingArea;
		this.district = builder.district;
		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.areaRentFrom = builder.areaRentFrom;
		this.areaRentTo = builder.areaRentTo;
		this.buildingTypes = builder.buildingTypes; 
	}

	public static class Builder {

		private String name;
		private String ward;
		private String street;
		private String numberOfBasement;
		private String buildingArea;
		private String district;
		private String costRentFrom;
		private String costRentTo;
		private String areaRentFrom;
		private String areaRentTo;
		private String[] buildingTypes = new String[] {};
		
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setNumberOfBasement(String numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}


		public Builder setBuildingArea(String buildingArea) {
			this.buildingArea = buildingArea;
			return this;
		}
		
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}

		public Builder setAreaRentFrom(String areaRentFrom) {
			this.areaRentFrom = areaRentFrom;
			return this;
		}

		public Builder setAreaRentTo(String areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}

		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}				
	}
}
