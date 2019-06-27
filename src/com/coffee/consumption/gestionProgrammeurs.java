package com.coffee.consumption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.coffee.consumption.sqlconnection.MySqlConnection;

public class gestionProgrammeurs {

	// Création de la table programmeurs
	public static void creerEtInitialiserTable() {

		Connection conn = MySqlConnection.getMySQLConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.execute("CREATE TABLE PROGRAMMERS (NAME VARCHAR(250), DAYS VARCHAR(250), CUPS INT)");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Table does not exists !");
		}

	}

	// Destruction de la table programmeurs.
	public static void supprimerTable() {

		Connection conn = MySqlConnection.getMySQLConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.execute("DROP TABLE PROGRAMMERS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Table does not exists !");
		}

	}

	// Intialiasitation de la table programmeurs à partir des données lues dans un
	// fichier texte.
	public static void chargerBase() {
		try {
			Connection con = MySqlConnection.getMySQLConnection();
			Statement stmt = con.createStatement();

			BufferedReader br = new BufferedReader(new FileReader(new File("C://programmeurs.txt")));
			String line = br.readLine();
			while (line != null && !line.trim().equals("")) {
				String[] vals = line.split("\\s+");
				System.out.println(vals);
				stmt.execute("INSERT INTO PROGRAMMERS VALUES ('" + vals[0] + "','" + vals[1] + "'," + vals[2] + ")");
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Affiche le nom de la personne qui a consommé le plus de tasses de café en une
	 * journée ainsi que sa consommation ce jour la, puis la liste des personnes
	 * ordonnee par ordre décroissant du nombre de consommations.
	 */
	public static void nbreTassesMax() {
		try {
			Connection conn = MySqlConnection.getMySQLConnection();
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT NAME, DAYS, CUPS FROM `PROGRAMMERS` WHERE (DAYS, CUPS) IN (SELECT  DAYS, MAX(CUPS) AS MAXCUPS FROM `PROGRAMMERS` GROUP BY  DAYS) ORDER BY CUPS DESC");
			while (rs.next()) {
				System.out.println(rs.getString("NAME") + "," + rs.getString("DAYS") + "," + rs.getInt("CUPS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Affiche le nombre total de tasses consommées.

	public static int nbreTotalTasses() {
		int count = 0;
		try {
			Connection conn = MySqlConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT SUM(CUPS) FROM PROGRAMMERS");
			// ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * Renvoie le nombre total de tasses consommées par un programmeur donné et
	 * affiche le détail des consommations de celui-ci.
	 */
	public static int nbreTotalTassesPgm(String name) {
		int count = 0;
		try {
			Connection conn = MySqlConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT SUM(CUPS) FROM PROGRAMMERS WHERE NAME = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
