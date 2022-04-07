package org.dzsystems.dao;

import org.dzsystems.model.Well;

import java.util.List;
import java.util.Set;

public interface WellDao {
	Well saveWell(Well well);
	Well findWellByName(String name);
	List<Well> findAllWellsByNameSet(Set<String> wellNameSet);
	List<Well> findAllWells();
	boolean wellExistsByName(String wellName);
}
