package org.dzsystems.model;

import java.util.Objects;

public class Well implements Comparable<Well> {

	private Integer id;
	private String name;

	public Well(String name) {
		this.name = name;
	}

	public Well() {

	}

	public Well(int id, String name) {
		this.id = id;
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

	@Override
	public int compareTo(Well o) {
		return this.getName().compareToIgnoreCase(o.getName());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Well well = (Well) o;
		return Objects.equals(id, well.id) && Objects.equals(name, well.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
