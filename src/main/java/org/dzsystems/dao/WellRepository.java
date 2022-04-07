package org.dzsystems.dao;

import org.dzsystems.dao.implement.EquipmentDaoImpl;
import org.dzsystems.dao.implement.WellDaoImpl;
import org.dzsystems.model.Equipment;
import org.dzsystems.model.Well;

import java.util.List;
import java.util.Set;

public class WellRepository implements WellDao, EquipmentDao {

	private final WellDao wellDao = WellDaoImpl.getInstance();
	private final EquipmentDao equipmentDao = EquipmentDaoImpl.getInstance();


	@Override
	public List<Equipment> findAllEquipmentByWellID(int wellId) {
		return equipmentDao.findAllEquipmentByWellID(wellId);
	}

	@Override
	public void saveAllEquipmentWithWellId(List<Equipment> equipmentList, int wellId) {
		equipmentDao.saveAllEquipmentWithWellId(equipmentList, wellId);
	}

	@Override
	public String findLastRecordNameFromEquipment() {
		return equipmentDao.findLastRecordNameFromEquipment();
	}

	@Override
	public Well saveWell(Well well) {
		return wellDao.saveWell(well);
	}

	@Override
	public Well findWellByName(String name) {
		return wellDao.findWellByName(name);
	}

	@Override
	public List<Well> findAllWellsByNameSet(Set<String> wellNameSet) {
		return wellDao.findAllWellsByNameSet(wellNameSet);
	}

	@Override
	public List<Well> findAllWells() {
		return wellDao.findAllWells();
	}

	@Override
	public boolean wellExistsByName(String wellName) {
		return wellDao.wellExistsByName(wellName);
	}
}
