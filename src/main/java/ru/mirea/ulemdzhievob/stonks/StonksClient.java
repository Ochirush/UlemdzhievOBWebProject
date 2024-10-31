
package ru.mirea.ulemdzhievob.stonks;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

public class StonksClient {
    private final Retrofit client;
    private final StonkService stonksService;
    private final DatabaseService databaseService;


    public StonksClient() throws SQLException {
        client = new Retrofit.Builder()
                .baseUrl("https://www.cbr.ru")
                .addConverterFactory(JacksonConverterFactory.create(new XmlMapper()))
                .build();

        stonksService = client.create(StonkService.class);
        databaseService = new DatabaseServiceImpl();
    }

    public void fetchAndSaveMaxValute() {
        LocalDate myBD = LocalDate.of(2005, 07, 12);
        try {
            Response<DailyCurs> response = stonksService.getDailyCurs(
                    myBD.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            ).execute();


            if (response.isSuccessful() && response.body() != null) {
                DailyCurs dailyCurs = response.body();
                Optional<Valute> maxValute = dailyCurs.getValutes().stream()
                        .filter(valute -> !valute.getName().equals("СДР (специальные права заимствования)"))
                        .max(Comparator.comparingDouble(Valute::getValue));

                if (maxValute.isPresent()) {
                    Valute mv = maxValute.get();
                    System.out.println(mv);
                    databaseService.saveMaxValuteOfDate("фамилияио", mv, myBD);
                }

                Valute valute = databaseService.getValuteOfDate(myBD);
            } else {
                System.out.println("Error fetching data from the service: " + response.code());
            }
        } catch (IOException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
