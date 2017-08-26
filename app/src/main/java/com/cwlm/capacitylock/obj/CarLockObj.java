package com.cwlm.capacitylock.obj;

public class CarLockObj {
	private String carLockId;
	private Integer routerId;// 接入单元编号1~65535（总机编号）
	private Integer addr;// 车位锁编号：1~250，注：此地址为二级编号
	private Long sortId;// 排序字段
	//下面三个维度控制车位锁的实时状态
	private Integer state;//分享与否，0表示分享，1表示未分享
	private Integer upordown;//空闲与否，0表示未空闲(车位锁降下去)，1表示空闲(车位锁升起来)
	private Integer predetermine;//预定与否，1表示被预定，0表示未被预定
	private Integer manGrade;
	private String managerId;
	private String managerOpenId;
	private String stopPlaceId;
	private String addTime;
	private String longitude;
	private String latitude;
	private String parkNumber;
	private Integer  isPri;
	private String floor;

	public Integer getIsPri() {
		return isPri;
	}
	public void setIsPri(Integer isPri) {
		this.isPri = isPri;
	}
	public String getManagerOpenId() {
		return managerOpenId;
	}
	public void setManagerOpenId(String managerOpenId) {
		this.managerOpenId = managerOpenId;
	}
	public Integer getPredetermine() {
		return predetermine;
	}
	public void setPredetermine(Integer predetermine) {
		this.predetermine = predetermine;
	}

	public String getCarLockId() {
		return carLockId;
	}
	public void setCarLockId(String carLockId) {
		this.carLockId = carLockId;
	}
	public Integer getRouterId() {
		return routerId;
	}
	public void setRouterId(Integer routerId) {
		this.routerId = routerId;
	}
	public Integer getAddr() {
		return addr;
	}
	public void setAddr(Integer addr) {
		this.addr = addr;
	}
	public Long getSortId() {
		return sortId;
	}
	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getManGrade() {
		return manGrade;
	}
	public void setManGrade(Integer manGrade) {
		this.manGrade = manGrade;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getStopPlaceId() {
		return stopPlaceId;
	}
	public void setStopPlaceId(String stopPlaceId) {
		this.stopPlaceId = stopPlaceId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Integer getUpordown() {
		return upordown;
	}
	public void setUpordown(Integer upordown) {
		this.upordown = upordown;
	}
	public String getParkNumber() {
		return parkNumber;
	}
	public void setParkNumber(String parkNumber) {
		this.parkNumber = parkNumber;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}

}
