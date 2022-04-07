package org.dzsystems;

import org.dzsystems.dao.implement.DataBaseHandler;
import org.dzsystems.model.Equipment;
import org.dzsystems.model.Well;
import org.dzsystems.service.implement.ExportHandler;
import org.dzsystems.service.WellService;
import org.dzsystems.service.implement.WellServiceImpl;
import org.dzsystems.utils.PrintHandler;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

public class Main implements Runnable {

	private final Scanner scanner = new Scanner(System.in);

	private WellService wellService = WellServiceImpl.getInstance();
	private boolean running = true;
	private final String GREETING_PROMPT = "" +
			"=================================================\n" +
			"||                 DZ * SYSTEMS                ||\n" +
			"||                    WELCOME                  ||\n" +
			"||              Oil Wells equipment            ||\n" +
			"||             record and management           ||\n" +
			"||                                             ||\n" +
			"|| enter 'h' for Help                          ||\n" +
			"=================================================";

	private final String COMMAND_PROMPT = "\n" +
//			"=================================================\n" +
			" AVAILABLE COMMANDS:                             \n" +
			" 1 to Create equipment on the Well               \n" +
			" 2 to Print equipment info                       \n" +
			" 3 to Export equipment info                      \n" +
			" 4 to Exit                                       \n" +
			"=================================================";

	private final String BYE_PROMPT = "" +
			"=================================================\n" +
			" Exiting...                                      \n" +
			" Bye!                                            \n" +
			"=================================================";

	public void run() {
		DataBaseHandler.getConnection();
		System.out.println(GREETING_PROMPT);
		label:
		while (running) {
			System.out.print("Enter a command > ");
			String prompt = scanner.nextLine().toLowerCase(Locale.ROOT);
			switch (prompt) {
				case "1":
					createEquipment();
					break;
				case "2":
					printEquipmentTable();
					break;
				case "3":
					exportToXML();
					break;
				case "4":
					running = false;
					break label;
				case "h":
					System.out.println(COMMAND_PROMPT);
					break;
				default:
					System.out.println("Invalid command! Enter 'h' for 'Help'");
					break;
			}
		}
		DataBaseHandler.closeConnection();
		System.out.println(BYE_PROMPT);
	}

	private void createEquipment() {
		System.out.println("Creating equipment");
		Set<Equipment> equipmentSet = new HashSet<>();
		String wellName = "";
		int equipmentAmount = 0;
		try {
			System.out.print("Please enter Well name: ");
			wellName = scanner.nextLine().trim();
			if (wellName.contains(" ")) {
				throw new IllegalArgumentException("Please enter Well name without whitespaces");
			}
			System.out.print("Please enter equipment amount: ");
			equipmentAmount = Integer.parseInt(scanner.nextLine());
			wellService.createEquipment(wellName, equipmentAmount);
			System.out.println("Equipment created on the well: " + wellName);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void printEquipmentTable() {
		System.out.print("Please enter well names separated by spaces: ");
		Set<String> wantedNames = Arrays.stream(scanner.nextLine()
				.split("\\s++")).collect(Collectors.toSet());
		PrintHandler.printEquipmentTableMap(wellService.getAllWellsByNameSet(wantedNames));
	}

	private void exportToXML() {
		System.out.print("Please enter name of the file: ");
		String fileName = scanner.nextLine().toLowerCase(Locale.ROOT);
		Map<Well, List<Equipment>> listMap = wellService.getAllWells();
		ExportHandler.export(fileName, listMap);
	}
}
