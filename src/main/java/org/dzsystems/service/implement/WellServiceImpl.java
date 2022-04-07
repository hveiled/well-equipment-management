package org.dzsystems.service.implement;

import org.dzsystems.dao.WellRepository;
import org.dzsystems.model.Equipment;
import org.dzsystems.model.Well;
import org.dzsystems.service.EquipmentNamingService;
import org.dzsystems.service.WellService;

import java.util.*;

/**
 * Implementation of the WellService.
 *
 * Class implements WellService The service Provides methods allowing operate with Wells
 * @author Andrei Ivanov
 * @see WellRepository
 * @see EquipmentNamingService
 */
public class WellServiceImpl implements WellService {

	private static volatile WellServiceImpl instance;
	private final WellRepository repository = new WellRepository();
	private final EquipmentNamingService namingService = EquipmentNamingServiceImpl.getInstance();

	/**
	 * Method creating singleton instance of the WellService
	 *
	 * @return instance of WellServiceImpl
	 * @see WellServiceImpl
	 */
	public static synchronized WellServiceImpl getInstance() {
		if (instance == null)
			synchronized (WellServiceImpl.class) {
				if (instance == null)
					instance = new WellServiceImpl();
			}
		return instance;
	}

	/**
	 * Method allows to retrieve data from database by name set;
	 *
	 * @param nameSet Set of strings: well names
	 * @return Equipment lists witch are mapped to the certain Wells
	 * @see Well
	 * @see Equipment
	 */
	public Map<Well, List<Equipment>> getAllWellsByNameSet(Set<String> nameSet) {
		Map<Well, List<Equipment>> wellListMap = new HashMap<>();
		List<Well> wells = repository.findAllWellsByNameSet(nameSet);
		for (Well well : wells) {
			List<Equipment> equipment = repository.findAllEquipmentByWellID(well.getId());
			wellListMap.put(well, equipment);
		}
		return wellListMap;
	}

	/**
	 * Method allows to retrieve data from database by name set;
	 *
	 * @return Equipment lists witch are mapped to the certain Wells
	 * @see Well
	 * @see Equipment
	 */
	public synchronized Map<Well, List<Equipment>> getAllWells() {
		Map<Well, List<Equipment>> wellListMap = new HashMap<>();
		List<Well> wells = repository.findAllWells();
		for (Well well : wells) {
			List<Equipment> equipment = repository.findAllEquipmentByWellID(well.getId());
			wellListMap.put(well, equipment);
		}
		return wellListMap;
	}

	/**
	 * Allows to create new Well or update existing Well by adding a new Equipment
	 *
	 * @param wellName Name of the existing Well or a creating Well
	 * @param equipmentUnitNumber number of creating Equipment on the Well
	 * @see Well
	 * @see Equipment
	 */
	public synchronized boolean createEquipment(String wellName, int equipmentUnitNumber) throws IllegalArgumentException {
		int wellNameLength = 32;
		if (wellName.length() > wellNameLength) {
			throw new IllegalArgumentException("Well name length: " + wellName + " must not be longer than " + wellNameLength);
		}
		if (equipmentUnitNumber <= 0) {
			throw new IllegalArgumentException("Equipment unit number: " + equipmentUnitNumber + " must not be less than one");
		}
		List<Equipment> equipmentSet = new ArrayList<>();
		Well well = repository.findWellByName(wellName);
		if (well == null) {
			well = createWell(wellName);
		}
		for (int i = 0; i < equipmentUnitNumber; i++) {
			Equipment equipment = new Equipment(namingService.createEquipmentName());
			equipmentSet.add(equipment);
		}
		repository.saveAllEquipmentWithWellId(equipmentSet, well.getId());
		return repository.wellExistsByName(well.getName());
	}

	/**
	 * Allows to create a new Well
	 *
	 * @param wellName Name of the creating Well
	 * @return Returns the newly created entity
	 */
	public Well createWell(String wellName) {
		Well well = new Well(wellName);
		return repository.saveWell(well);
	}

}
