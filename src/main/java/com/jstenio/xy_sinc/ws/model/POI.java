package com.jstenio.xy_sinc.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name="poi_")
public class POI {
	
	
	@Id @GeneratedValue
	private Integer id;
	
	private String name;
	@Min(value=0, message = "Coordenada nao pode ser negativa")
	@Column(name="x_")
	private int x;
	@Min(value=0, message = "Coordenada nao pode ser negativa")
	@Column(name="y_")
	private int y;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
