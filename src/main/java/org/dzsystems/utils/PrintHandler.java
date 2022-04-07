package org.dzsystems.utils;

import org.dzsystems.model.Equipment;
import org.dzsystems.model.Well;

import java.util.*;


/**
 * Table writer class.
 *
 * @version 1.0
 * @author Andrew Ivanov
 */
public class PrintHandler {

	/**
	 * Method prints to the stdout mapped data
	 *
	 * @param map printed data
	 * @see Well
	 * @see Equipment
	 */
	public static void printEquipmentTableMap(Map<Well, List<Equipment>> map) {
		TreeMap<Well, List<Equipment>> sortedByKeyMap = new TreeMap<>(map);
		Formatter formatter = new Formatter();
		formatter.format("EQUIPPED OIL WELLS\n");
		formatter.format("%1s\n", "______________________________________________________________");
		formatter.format("%1s%32s%2s%25s%2s\n", "|", "Well name", "|", "Equipment amount, items", "|");
		formatter.format("%1s\n", "______________________________________________________________");
		for (Map.Entry<Well, List<Equipment>> set : sortedByKeyMap.entrySet()) {
			formatter.format("%1s%32s%2s%25s%2s\n", "|", set.getKey().getName(), "|", set.getValue().size(), "|");
		}
		int totalEquipmentAmount = sortedByKeyMap.values().stream().mapToInt(List::size).sum();
		formatter.format("%1s\n", "______________________________________________________________");
		formatter.format("%1s%32s%2s%25s%2s\n", "|", "Total", "|", totalEquipmentAmount, "|");
		formatter.format("%1s\n", "______________________________________________________________");
		System.out.println(formatter);
	}
}
