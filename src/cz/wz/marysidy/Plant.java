package cz.wz.marysidy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    // CONSTRUCTORS
    // jeden pro nastavení všech atributů
    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    // druhý nastaví jako poznámku prázdný řetězec a datum zasazení i datum poslední zálivky nastaví na dnešní datum
    public Plant(String name, int frequencyOfWatering){
        this(name, "", LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }

    // třetí nastaví totéž co druhý a navíc výchozí frekvenci zálivky na 7 dnů
    public Plant(String name){
        this(name, 7);
    }

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWateringInfo(){
        return name + ", datum poslední zálivky " + watering.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                + ", datum doporučené další zálivky " + nextWatering();
    }

    public String nextWatering(){
        return (watering.plusDays(frequencyOfWatering)).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void doWateringNow(){
        watering = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", planted=" + planted +
                ", watering=" + watering +
                ", frequencyOfWatering=" + frequencyOfWatering +
                '}';
    }
}
