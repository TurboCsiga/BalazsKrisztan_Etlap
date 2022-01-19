package com.example.etlap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtlapDb {
    Connection conn;

    public EtlapDb() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/etlapdb", "root", "");
    }

    public List<Etel> getEtlap() throws SQLException {
        List<Etel> etlap = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM etlap;";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String nev = result.getString("nev");
            String leiras = result.getString("leiras");
            int ar = result.getInt("ar");
            String kategoria = result.getString("kategoria");
            Etel etel = new Etel(id, nev, leiras, ar, kategoria);
            etlap.add(etel);
        }
        return etlap;
    }

    public int filmHozzaadasa(String nev, String leiras, int ar, String kategoria) throws SQLException {
        String sql = "INSERT INTO etlap(nev, leiras, ar, kategoria) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nev);
        stmt.setString(2, leiras);
        stmt.setInt(3, ar);
        stmt.setString(4, kategoria);
        return stmt.executeUpdate();
    }

    public boolean etelTorlese(int id) throws SQLException {
        String sql = "DELETE FROM etlap WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int erintettSorok = stmt.executeUpdate();
        return erintettSorok == 1;
    }

    public boolean etelModositasa(Etel modositando) throws SQLException {
        String sql = "UPDATE etlap SET " +
                "nev = ?," +
                "leiras = ?," +
                "ar = ?," +
                "kategoria = ? " +
                "WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, modositando.getNev());
        stmt.setString(2, modositando.getLeiras());
        stmt.setInt(3, modositando.getAr());
        stmt.setString(4, modositando.getKategoria());
        stmt.setInt(5, modositando.getId());
        int erintettSorok = stmt.executeUpdate();
        return erintettSorok == 1;
    }
}
