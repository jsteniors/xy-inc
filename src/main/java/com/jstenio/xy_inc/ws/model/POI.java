package com.jstenio.xy_inc.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

@Entity
@Table(name="poi_", uniqueConstraints=@UniqueConstraint(columnNames= {"name", "x_", "y_"}))
@NamedQuery(name="poiByDistance", query="SELECT poi FROM POI poi WHERE SQRT((poi.x-:pX)*(poi.x-:pX)+(poi.y-:pY)*(poi.y-:pY))<=:pDistance")
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
	
	public POI() {
		
	}
	
	public POI(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

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
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POI other = (POI) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	
}
