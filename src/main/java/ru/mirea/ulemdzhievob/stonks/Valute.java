package ru.mirea.ulemdzhievob.stonks;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.mirea.ulemdzhievob.stonks.util.DoubleDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Valute {

    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    private String id;

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "Value")
    @JsonDeserialize(using = DoubleDeserializer.class)
    private Double value;

    public Valute(String id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public Valute(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Valute() {
    }

    @Override
    public String toString() {
        return "Valute{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}