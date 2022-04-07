package org.dzsystems.dao;

import org.dzsystems.model.Equipment;

import java.util.List;

public interface EquipmentDao {
	List<Equipment> findAllEquipmentByWellID(int wellId);
	void saveAllEquipmentWithWellId(List<Equipment> equipmentList, int wellId);
	String findLastRecordNameFromEquipment();
}
