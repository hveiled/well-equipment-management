package org.dzsystems.dao.implement;

import org.dzsystems.dao.EquipmentDao;
import org.dzsystems.model.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDaoImpl implements EquipmentDao {

	private static volatile EquipmentDaoImpl instance;
	private final Connection connection = DataBaseHandler.getConnection();

	public static synchronized EquipmentDaoImpl getInstance() {
		if (instance == null)
			synchronized (EquipmentDaoImpl.class) {
				if (instance == null)
					instance = new EquipmentDaoImpl();
			}
		return instance;
	}

	public synchronized List<Equipment> findAllEquipmentByWellID(int wellId) {
		List<Equipment> equipmentList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM equipment WHERE well_id=?");
			statement.setInt(1, wellId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Equipment equipment = new Equipment();
				equipment.setId(rs.getInt(1));
				equipment.setName(rs.getString(2));
				equipment.setWell_id(rs.getInt(3));
				equipmentList.add(equipment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipmentList;
	}

	public synchronized void saveAllEquipmentWithWellId(List<Equipment> equipmentList, int wellId) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("INSERT INTO equipment (name, well_id) VALUES (?, ?)");
			for (Equipment eq : equipmentList) {
				statement.setString(1, eq.getName());
				statement.setInt(2, wellId);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String findLastRecordNameFromEquipment() {

		String name = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM equipment WHERE id = (SELECT MAX(id) FROM equipment)");
			while (rs.next()) {
				name = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
}
