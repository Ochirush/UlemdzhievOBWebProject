
package ru.mirea.ulemdzhievob.stonks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseServiceImpl implements DatabaseService {
    private static final String DB_URL = "jdbc:postgresql://services.tms-studio.ru:8095/service_db";

    private Connection conn;

    @Override
    public Valute getValuteOfDate(LocalDate date) throws SQLException {
        PreparedStatement select = conn.prepareStatement(
                "SELECT * FROM val_curs WHERE date = ?");
        select.setObject(1, date);
        ResultSet resultSet = select.executeQuery();
        if (resultSet.next()) {
            return new Valute(resultSet.getString(2), resultSet.getDouble(3));
        }
        return null;
    }

    @Override
    public void saveMaxValuteOfDate(String fio, Valute valute, LocalDate date) throws SQLException {
        PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO val_curs (fio, valute_name, value, date) VALUES (?, ?, ?, ?)");
        insert.setObject(1, fio);
        insert.setObject(2, valute.getName());
        insert.setObject(3, valute.getValue());
        insert.setObject(4, date);
        insert.executeUpdate();
    }

    public DatabaseServiceImpl() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, "service_admin", "srv0983_");
        System.out.println("Connection is " + (conn.isValid(0) ? "up" : "down"));
    }
}
