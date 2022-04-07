package org.dzsystems.service;

import org.dzsystems.model.Equipment;
import org.dzsystems.model.Well;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WellService {
	boolean createEquipment(String wellName, int equipmentUnitNumber);
	Map<Well, List<Equipment>> getAllWellsByNameSet(Set<String> names);
	Map<Well, List<Equipment>> getAllWells();
}
