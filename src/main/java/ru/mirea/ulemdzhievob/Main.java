package ru.mirea.ulemdzhievob;
import java.sql.SQLException;


import ru.mirea.ulemdzhievob.stonks.StonksClient;

public class Main {
    public static void main(String[] args) {

        try {
            StonksClient client = new StonksClient();


            client.fetchAndSaveMaxValute();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}