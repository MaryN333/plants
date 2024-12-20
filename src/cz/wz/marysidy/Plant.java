package cz.wz.marysidy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Plant implements Comparable<Plant>{
    private String name;
    private String notes;
    private int frequencyOfWatering;
    private LocalDate planted;
    private LocalDate watering;

    // CONSTRUCTORS
    // jeden pro nastavení všech atributů
    public Plant(String name, String notes, int frequencyOfWatering, LocalDate watering, LocalDate planted) throws PlantException{
        if (frequencyOfWatering <= 0) {
            throw new PlantException(name + ". The object was NOT created. Frequency of watering must be be greater than zero.");
        }
        if (watering.isBefore(planted)) {
            throw new PlantException(name + ". The object was NOT created. The last watering date cannot be earlier than the planted date.");
        }
        this.name = name;
        this.notes = notes;
        this.frequencyOfWatering = frequencyOfWatering;
        this.watering = watering;
        this.planted = planted;
    }

    // druhý nastaví jako poznámku prázdný řetězec a datum zasazení i datum poslední zálivky nastaví na dnešní datum
    public Plant(String name, int frequencyOfWatering) throws PlantException{
        this(name, "", frequencyOfWatering, LocalDate.now(), LocalDate.now());
    }

    // třetí nastaví totéž co druhý a navíc výchozí frekvenci zálivky na 7 dnů
    public Plant(String name) throws PlantException{
        this(name, 7);
    }

    // Three methods for creating Plant
    public static Plant createPlant(String name, String notes, int frequencyOfWatering, LocalDate watering, LocalDate planted) {
        try {
            return new Plant(name, notes, frequencyOfWatering, watering, planted);
        } catch (PlantException e) {
            System.err.println("Error creating plant: " + e.getMessage());
            return null;
        }
    }

    public static Plant createPlant(String name, int frequencyOfWatering) {
        return createPlant(name, "", frequencyOfWatering, LocalDate.now(), LocalDate.now());
    }

    public static Plant createPlant(String name) {
        return createPlant(name, 7);
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

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)){
            throw new PlantException("It`s impossible to set the last watering date earlier than the planted date.");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException{
        if (frequencyOfWatering <= 0) {
            throw new PlantException("It`s impossible to set the frequency of watering, must be greater than zero.");
        }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant plant)) return false;
        return getFrequencyOfWatering() == plant.getFrequencyOfWatering() &&
                Objects.equals(getName(), plant.getName()) &&
                Objects.equals(getNotes(), plant.getNotes()) &&
                Objects.equals(getPlanted(), plant.getPlanted()) &&
                Objects.equals(getWatering(), plant.getWatering());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNotes(), getFrequencyOfWatering(), getWatering(), getPlanted());
    }

    @Override
    public String toString() {
        String shortNotes = notes.length() > 30 ? notes.substring(0, 30) + "..." : notes;
        return String.format("%-35s %-35s %-5s %-15s %-15s",
                name,
                shortNotes,
                frequencyOfWatering,
                watering,
                planted);
    }

    @Override
    public int compareTo(Plant o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
