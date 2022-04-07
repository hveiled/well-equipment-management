package org.dzsystems.dao.implement;

import org.dzsystems.dao.WellDao;
import org.dzsystems.model.Well;

import java.sql.*;
import java.util.*;

public class WellDaoImpl implements WellDao {

	private static volatile WellDaoImpl instance;
	private final Connection connection = DataBaseHandler.getConnection();

	public static synchronized WellDaoImpl getInstance() {
		if (instance == null)
			synchronized (WellDaoImpl.class) {
				if (instance == null)
					instance = new WellDaoImpl();
			}
		return instance;
	}

	public synchronized Well saveWell(Well well) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO wells (name) VALUES (?)");
			statement.setString(1, well.getName());
			statement.execute();

			return findWellByName(well.getName());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public synchronized Well findWellByName(String name) {
		Well well = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM wells WHERE name=?");
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				well = new Well();
				well.setId(rs.getInt(1));
				well.setName(rs.getString(2));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return well;
	}

	public synchronized List<Well> findAllWellsByNameSet(Set<String> wellNameSet) {
		List<Well> wells = new ArrayList<>();
		for (String wellName : wellNameSet) {
			Well storedWell = findWellByName(wellName);
			if (storedWell != null) {
				wells.add(storedWell);
			}
		}
		return wells;
	}

	public synchronized List<Well> findAllWells() {
		List<Well> wells = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM wells");
			if (resultSet == null) {
				return null;
			}
			wells = new ArrayList<>();
			while (resultSet.next()) {
				Well well = new Well(resultSet.getInt(1), resultSet.getString(2));
				wells.add(well);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return wells;
	}

	public synchronized boolean wellExistsByName(String wellName) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM wells WHERE name=?");
			statement.setString(1, wellName);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
