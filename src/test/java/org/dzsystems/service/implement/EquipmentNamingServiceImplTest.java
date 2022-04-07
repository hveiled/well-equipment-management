package org.dzsystems.service.implement;

import org.dzsystems.service.EquipmentNamingService;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentNamingServiceImplTest {

	@org.junit.jupiter.api.Test
	void createEquipmentName() {
		EquipmentNamingService service = new EquipmentNamingServiceImpl();
		String name1 = service.createEquipmentName();
		String name2 = service.createEquipmentName();
		assertEquals (name1, "EQ0001");
		assertEquals(name2, "EQ0002");
	}
}