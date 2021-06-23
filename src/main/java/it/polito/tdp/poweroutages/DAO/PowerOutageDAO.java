package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<Poweroutage> getTuttiEventi() {
		String sql ="SELECT p.id AS id, p.nerc_id AS nercId, p.date_event_began AS dataBegan, p.date_event_finished AS dataFinished, p.customers_affected AS costumersAffected, YEAR(p.date_event_began) AS anno "
				+"FROM poweroutages p";
;
	//			+"WHERE p.nerc_id = ?";
		List<Poweroutage> result = new LinkedList<Poweroutage>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
//			st.setInt(1, nercId);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Poweroutage n = new Poweroutage(res.getInt("id"), res.getInt("nercId"), res.getTimestamp("dataBegan"), res.getTimestamp("dataFinished"),res.getInt("costumersAffected"));
				result.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
	}
	

}
