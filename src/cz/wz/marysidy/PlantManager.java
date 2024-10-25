package cz.wz.marysidy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

}
