package cz.wz.marysidy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILENAME = "resources/kvetiny-spatne-datum.txt";
    private static final String DELIMITER = "\t";

    private static void readFromFile(PlantManager manager, String fileName) {
        try {
            manager.readContentFromFile(fileName, DELIMITER);
            List<Plant> plants = manager.getPlantList();
            for (Plant plant : plants) {
                System.out.println(plant);
            }
        } catch (PlantException e){
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            System.out.println("Size of plantList after error: " + manager.getPlantList().size());
        }
    }

    private static void saveToFile(PlantManager manager, String saveToFile){
        try {
            manager.saveContentToFile(saveToFile, DELIMITER);
//            manager.getPlantList().forEach(System.out::println);
        } catch (PlantException e) {
            System.err.println("An error occurred while saving the file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        PlantManager manager1 = new PlantManager();
        Plant p1 = Plant.createPlant("Dendrobium nobile", "Dendrobium nobile is one of the most widespread ornamental members of the orchid family",
                3, LocalDate.of(2024, 10, 15),
                LocalDate.of(2024, 10, 18));
        manager1.addPlant(p1);
        if (p1 != null) {
            System.out.println(p1.getWateringInfo());
            p1.doWateringNow();
            System.out.println(p1.getWateringInfo());
        }

        Plant p2 = Plant.createPlant("Phalaenopsis", -3);
        manager1.addPlant(p2);

        Plant p3 = Plant.createPlant("Cattleya");
        manager1.addPlant(p3);

        // I did not add p4 in any PlantMananager
        Plant p4 = Plant.createPlant("Oncidium");

        Plant p5 = Plant.createPlant("Vanda", "These mostly epiphytic, but sometimes lithophytic or terrestrial orchids, are distributed in India, Himalaya..",
                3, LocalDate.of(2024, 10, 15),
                LocalDate.of(2024, 10, 18));
        manager1.addPlant(p5);
        Plant p6 = Plant.createPlant("Brassia", "The genus was named after William Brass, a British botanist and illustrator",
                2, LocalDate.of(2024, 10, 14),
                LocalDate.of(2024, 10, 15));
        manager1.addPlant(p6);


        System.out.println("-*".repeat(20));
        manager1.getPlantList().forEach(System.out::println);

        System.out.println("-*".repeat(20));
        System.out.println(manager1.getPlant(0));

        System.out.println("-*".repeat(20));
        System.out.println("Need to water:");
        ArrayList<Plant> wateringList = manager1.needToWatering();
//        wateringList.forEach(plant -> System.out.println(plant));
        wateringList.forEach(System.out::println);

        System.out.println("-*".repeat(20));
        manager1.removePlant(p1);
        manager1.getPlantList().forEach(System.out::println);
        manager1.removePlant(p4);

        System.out.println("-*".repeat(20));
        System.out.println("Without sorting:");
        manager1.getPlantList().forEach(System.out::println);
        System.out.println("Sorting by name:");
        manager1.sortPlantsByName();
        manager1.getPlantList().forEach(System.out::println);
        System.out.println("Sorting by last watering:");
        manager1.sortPlantsByWatering();
        manager1.getPlantList().forEach(System.out::println);

        System.out.println("Task 6 -* ".repeat(10));
        PlantManager manager = new PlantManager();
        readFromFile(manager, FILENAME);
        System.out.println("Just for control. Size of plantList: " + manager.getPlantList().size());
        System.out.println("Continuation of the program");

        readFromFile(manager, "resources/kvetiny-spatne-frekvence.txt");
        System.out.println("Just for control. Size of plantList: " + manager.getPlantList().size());

        System.out.println("Continuation of the program again");
        System.out.println("SAVE TO FILE");
        PlantManager man = new PlantManager();
        readFromFile(man, "resources/flowers.txt");
        Plant plant = Plant.createPlant("Lily");
        man.addPlant(plant);
        saveToFile(man, "resources/flowers.txt");


    }


}
