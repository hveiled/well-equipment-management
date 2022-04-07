package org.dzsystems.service.implement;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dzsystems.model.Equipment;
import org.dzsystems.model.Well;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Export to XML handler class
 *
 * @author Andrei Ivanov
 * @version 1.0
 */
public class ExportHandler {

	/**
	 * File extension
	 */
	private static final String XML = ".xml";

	/**
	 * Creates a new file with the specified name;
	 *
	 * @param fileName file name
	 * @param data     Map witch is required to marshall to XML
	 */
	public static void export(String fileName, Map<Well, List<Equipment>> data) {

		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			Element dbinfo = document.createElement("dbinfo");
			document.appendChild(dbinfo);

			TreeMap<Well, List<Equipment>> sortedData = new TreeMap<>(data);

			for (Map.Entry<Well, List<Equipment>> set : sortedData.entrySet()) {
				Element well = document.createElement("well");
				dbinfo.appendChild(well);
				well.setAttribute("name", set.getKey().getName());
				well.setAttribute("id", String.valueOf(set.getKey().getId()));
				for (int i = 0; i < set.getValue().size(); i++) {
					Element equipment = document.createElement("equipment");
					well.appendChild(equipment);
					equipment.setAttribute("name", set.getValue().get(i).getName());
					equipment.setAttribute("id", String.valueOf(set.getValue().get(i).getId()));
				}
			}

			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			File file = new File(fileName + XML);
			StreamResult streamResultToFile = new StreamResult(file);
			try {
				transformer.transform(domSource, streamResultToFile);
			} catch (TransformerException ex) {
				ex.printStackTrace();
			}
			System.out.println("Done creating XML File: " + file.getAbsolutePath());

		} catch (ParserConfigurationException | TransformerException pce) {
			pce.printStackTrace();
		}
	}
}
