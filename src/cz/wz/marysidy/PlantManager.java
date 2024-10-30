package cz.wz.marysidy;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantManager{
    private List<Plant> plantList = new ArrayList<>();

    public List<Plant> getPlantList() {
        return new ArrayList<>(plantList);
    }

    /** Adds new plant to the list
     * @param plant is a Plant object you need to add
     */
    public void addPlant(Plant plant){
        if (plant != null) {
            plantList.add(plant);
            System.out.println("Plant added: " + plant.getName());
        } else {
            System.out.println("Plant is null and cannot be added.");
        }
    }

    public ArrayList<Plant> needToWatering(){
        ArrayList<Plant> wateringList = new ArrayList<>();
        for(Plant plant : plantList){
            if(plant.getWatering().plusDays(plant.getFrequencyOfWatering()).isBefore(LocalDate.now()) ||
                    plant.getWatering().plusDays(plant.getFrequencyOfWatering()).isEqual(LocalDate.now())){
                wateringList.add(plant);
            }
        }
        return wateringList;
    }

    // getting a plant at the specified index
    public Plant getPlant(int index){
        return plantList.get(index);
    }

    // removing a plant from the list
    public void removePlant(Plant plant){
        String name = plant.getName();
        boolean isRemoved = plantList.remove(plant);
        System.out.println(isRemoved ?
                "The plant " + name + " has been removed." : "The plant " + name + " was NOT removed, was not in the list");
    }

    // Sorting by name
    public void sortPlantsByName() {
        Collections.sort(plantList);
    }

    // Sorting by last watering
    public void sortPlantsByWatering() {
        Collections.sort(plantList, Comparator.comparing(Plant::getWatering));
    }

    public List<Plant> readContentFromFile(String fileName, String delimiter) throws PlantException {
        plantList.clear();
        int itemsRequired = 5;
        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                String[] parts = line.split(delimiter);
                if (parts.length != itemsRequired) {
                    throw new PlantException("Incorrect number of items! Expected " + itemsRequired
                            + " items\n" + line + "\nError on line " + lineNumber);
                }
                try {
                    String name = parts[0].trim();
                    String notes = parts[1].trim();
                    int frequencyOfWatering = Integer.parseInt(parts[2]);
                    LocalDate planted = LocalDate.parse(parts[3]);
                    LocalDate watering = LocalDate.parse(parts[4]);
                    plantList.add(new Plant(name, notes, frequencyOfWatering, planted, watering));
                } catch (DateTimeParseException e) {
                    System.err.println("Incorrect date format on line " + lineNumber + ": " + e.getMessage());
                    plantList.clear(); // Clear the list when there is a date format error
                    return plantList;
                } catch (NumberFormatException e) {
                    System.err.println("Incorrect number format on line " + lineNumber + ": " + e.getMessage());
                    plantList.clear();
                    return plantList;
                }
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("File " + fileName + " not found: " + e.getMessage());
        }
        return plantList;
    }

    public void saveContentToFile(String fileName, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plantList) {
                writer.println(
                        plant.getName() + delimiter
                                + plant.getNotes() + delimiter
                                + plant.getFrequencyOfWatering() + delimiter
                                + plant.getPlanted() + delimiter
                                + plant.getWatering());
            }
        } catch (IOException e) {
            throw new PlantException("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }
}
