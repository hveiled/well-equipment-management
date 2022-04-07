package org.dzsystems.service.implement;

import org.dzsystems.dao.EquipmentDao;
import org.dzsystems.dao.WellDao;
import org.dzsystems.dao.WellRepository;
import org.dzsystems.dao.implement.DataBaseHandler;
import org.dzsystems.dao.implement.EquipmentDaoImpl;
import org.dzsystems.dao.implement.WellDaoImpl;
import org.dzsystems.model.Equipment;
import org.dzsystems.model.Well;
import org.dzsystems.service.WellService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WellServiceImplTest {

	private WellServiceImpl service;
	private List<Well> wellList;
	private Set<String> nameSet;
	private List<Equipment> well_1_Equipment;
	private List<Equipment> well_2_Equipment;
	private List<Equipment> well_3_Equipment;
	private WellRepository repository;

	@BeforeEach
	void before() {
		repository = Mockito.mock(WellRepository.class);

		nameSet = Stream.of("Alpha", "Bravo", "Charly").collect(Collectors.toSet());

		wellList = Stream.of(
				new Well(1, "Alpha"),
				new Well(2, "Bravo"),
				new Well(3, "Charly")).collect(Collectors.toList());

		well_1_Equipment = Stream
				.of(new Equipment(1, "EQ0001", 1),
						new Equipment(2, "EQ0002", 1),
						new Equipment(3, "EQ0003", 1))
				.collect(Collectors.toList());

		well_2_Equipment = Stream
				.of(new Equipment(4, "EQ0004", 2),
						new Equipment(5, "EQ0005", 2),
						new Equipment(6, "EQ0006", 2))
				.collect(Collectors.toList());

		well_3_Equipment = Stream
				.of(new Equipment(7, "EQ0007", 3),
						new Equipment(8, "EQ0008", 3),
						new Equipment(9, "EQ0009", 3))
				.collect(Collectors.toList());

		Mockito.when(repository.findAllEquipmentByWellID(1)).thenReturn(well_1_Equipment);
		Mockito.when(repository.findAllEquipmentByWellID(2)).thenReturn(well_2_Equipment);
		Mockito.when(repository.findAllEquipmentByWellID(3)).thenReturn(well_3_Equipment);
		Mockito.when(repository.findAllWellsByNameSet(nameSet)).thenReturn(wellList);
	}

	@Test
	void testGetInstanceWhenServiceIsInstantiated() {
		service = WellServiceImpl.getInstance();
		assertNotNull(service);
	}

	@Test
	void testGetInstanceWhenServiceIsNotInstantiated() {
		assertNull(service);
	}

//	@Test
//	void getAllWellsByNames() {
//		service = WellServiceImpl.getInstance();
//
//		Map<Well, List<Equipment>> expectedResult = new HashMap<>();
//		expectedResult.put(new Well(1, "Alpha"), well_1_Equipment);
//		expectedResult.put(new Well(2, "Bravo"), well_2_Equipment);
//		expectedResult.put(new Well(3, "Charly"), well_3_Equipment);
//		Map<Well, List<Equipment>> actualResult = service.getAllWellsByNameSet(nameSet);
//		for (Map.Entry<Well, List<Equipment>> set : actualResult.entrySet()) {
//			System.out.println(set.getKey().getName());
//		}
//		assertNotNull(actualResult);
//		assertEquals(expectedResult.get(wellList.get(1)), actualResult.get(wellList.get(1)));
//		assertEquals(expectedResult.get(wellList.get(2)), actualResult.get(wellList.get(2)));
//		assertEquals(expectedResult.get(wellList.get(3)), actualResult.get(wellList.get(3)));
//	}
//
	@Test
	void getAllWells() {
		service = WellServiceImpl.getInstance();
	}

	@Test
	void testCreateEquipmentWhenNameIs32AndValidNumberThanDoesNotThrowsEx() {
		String longerThan32Name = "0ZOuKygbmrcnW5WAOiFQmjHtY2ZnSCo1";
		service = WellServiceImpl.getInstance();
		assertDoesNotThrow(() -> service.createEquipment(longerThan32Name, 3));
	}

	@Test
	void testCreateEquipmentWhenNameIsLongerThan32AndValidNumberThanThrowsEx() {
		String longerThan32Name = "Loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
				"oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong";
		service = WellServiceImpl.getInstance();
		assertThrows(IllegalArgumentException.class, () -> service.createEquipment(longerThan32Name, 3));
	}


	@Test
	void testCreateEquipmentWhenNameIsValidAndNumberIsLessThanOneThanThrowsEx() {
		String longerThan32Name = "Alpha";
		service = WellServiceImpl.getInstance();
		assertThrows(IllegalArgumentException.class, () -> service.createEquipment(longerThan32Name, 0));
	}

	@Test
	void testCreateEquipmentWhenNameIsValidAndNumberIsLessThanZeroThanThrowsEx() {
		String longerThan32Name = "Alpha";
		service = WellServiceImpl.getInstance();
		assertThrows(IllegalArgumentException.class, () -> service.createEquipment(longerThan32Name, -1));
	}

	@Test
	void createWell() {
		service = WellServiceImpl.getInstance();
		Well well = new Well(1, "Alpha");
		Mockito.when(repository.saveWell(well)).thenReturn(well);
		Well actualResult = service.createWell("Alpha");
		assertEquals("Alpha", actualResult.getName());
	}

}
