package com.rentbikes.model;

/*
 * 此类用于存放数据库中权限表的数据
 */
public class MSPhase {
	
	private int phaseId;	//权限编号
	private String url;		//路径
	private String description;		//备注
	
	public MSPhase(){}

	
	public int getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MSPhase [phaseId=" + phaseId + ", url=" + url
				+ ", description=" + description + "]";
	}
	
}
