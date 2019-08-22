package com.laptrinhjavaweb.dto;

public class BuildingDTO extends AbstractDTO<BuildingDTO>{
	
	private String name;
	private String ward;
	private String street;
	private String structure;
	private String numberOfBasement;
	private String buildingArea;
	private String district;
	private Integer costRent;
	private String costDescription;
	private String serviceCost;
	private String carCost;
	private String motorbikeCost;
	private String overtimeCost;
	private String electricityCost;
	private String deposit;
	private String payment;
	private String timeContract;
	private String timeDecorator;
	private String managerName;
	private String managerPhone;
	private String type;
	private String direction;
	private String level;
	private String address;
	private String rentArea;
	
	private String costRentFrom;
	private String costRentTo;
	private String areaRentFrom;
	private String areaRentTo;
	private String[] buildingTypes = new String[] {};
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	
	public String getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(String numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}
	public String getCostDescription() {
		return costDescription;
	}
	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}
	public String getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}
	public String getCarCost() {
		return carCost;
	}
	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}
	public String getMotorbikeCost() {
		return motorbikeCost;
	}
	public void setMotorbikeCost(String motorbikeCost) {
		this.motorbikeCost = motorbikeCost;
	}
	public String getOvertimeCost() {
		return overtimeCost;
	}
	public void setOvertimeCost(String overtimeCost) {
		this.overtimeCost = overtimeCost;
	}
	public String getElectricityCost() {
		return electricityCost;
	}
	public void setElectricityCost(String electricityCost) {
		this.electricityCost = electricityCost;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getTimeDecorator() {
		return timeDecorator;
	}
	public void setTimeDecorator(String timeDecorator) {
		this.timeDecorator = timeDecorator;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public Integer getCostRent() {
		return costRent;
	}
	public void setCostRent(Integer costRent) {
		this.costRent = costRent;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTimeContract() {
		return timeContract;
	}
	public void setTimeContract(String timeContract) {
		this.timeContract = timeContract;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getBuildingTypes() {
		return buildingTypes;
	}
	public void setBuildingTypes(String[] buildingTypes) {
		this.buildingTypes = buildingTypes;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCostRentFrom() {
		return costRentFrom;
	}
	public void setCostRentFrom(String costRentFrom) {
		this.costRentFrom = costRentFrom;
	}
	public String getCostRentTo() {
		return costRentTo;
	}
	public void setCostRentTo(String costRentTo) {
		this.costRentTo = costRentTo;
	}
	public String getAreaRentFrom() {
		return areaRentFrom;
	}
	public void setAreaRentFrom(String areaRentFrom) {
		this.areaRentFrom = areaRentFrom;
	}
	public String getAreaRentTo() {
		return areaRentTo;
	}
	public void setAreaRentTo(String areaRentTo) {
		this.areaRentTo = areaRentTo;
	}
	public String getAddress() {
		return this.street +", "+ this.ward;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
}
