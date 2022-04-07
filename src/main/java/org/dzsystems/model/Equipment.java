package org.dzsystems.model;

import java.util.Objects;

public class Equipment {

	private Integer id;
	private String name;
	private Integer well_id;

	public Equipment(String name, Integer well_id) {
		this.name = name;
		this.well_id = well_id;
	}


	public Equipment() {

	}

	public Equipment(Integer id, String name, Integer well_id) {
		this.id = id;
		this.name = name;
		this.well_id = well_id;
	}

	public Equipment(String name) {
		this.name = name;
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

	public Integer getWell_id() {
		return well_id;
	}

	public void setWell_id(Integer well_id) {
		this.well_id = well_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Equipment equipment = (Equipment) o;
		return Objects.equals(id, equipment.id) && Objects.equals(name, equipment.name) && Objects.equals(well_id, equipment.well_id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, well_id);
	}
}
