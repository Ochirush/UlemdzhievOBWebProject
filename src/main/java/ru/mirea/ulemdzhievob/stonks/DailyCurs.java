package ru.mirea.ulemdzhievob.stonks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;
import java.util.List;

public class DailyCurs {

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Valute> valutes;

    public DailyCurs() {
    }

    public DailyCurs(Date date, String name, List<Valute> valutes) {
        this.date = date;
        this.name = name;
        this.valutes = valutes;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<Valute> getValutes() {
        return valutes;
    }

    @Override
    public String toString() {
        return "DailyCurs{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", valutes=" + valutes +
                '}';
    }
}
