package org.dzsystems.service;


/**
 * Functional interface
 *
 * @author Andrei Ivanov
 * @version 1.0
 */
public interface EquipmentNamingService {

	/**
	 * Creates unique <code>Equipment</code> name
	 *
	 * @return Unique name of the <code>Equipment</code> of type <code><b>String</b></code>.
	 * */
	String createEquipmentName();
}
