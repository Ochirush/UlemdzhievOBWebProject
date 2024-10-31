package ru.mirea.ulemdzhievob.stonks;
import java.sql.SQLException;
import java.time.LocalDate;

public interface DatabaseService {
    Valute getValuteOfDate(LocalDate date) throws SQLException;

    void saveMaxValuteOfDate(String fio, Valute valute, LocalDate date) throws SQLException;
}
