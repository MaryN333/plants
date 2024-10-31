package cz.wz.marysidy;

import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final String FILENAME = "resources/kvetiny-spatne-datum.txt";
    private static final String DELIMITER = "\t";

    private static void readFromFile(PlantManager manager, String fileName) {
        try {
            manager.readContentFromFile(fileName, DELIMITER);
            List<Plant> plants = manager.getPlantList();
        } catch (PlantException e){
            System.err.println("An error occurred while reading the file: " + e.getMessage());
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
        // Načti seznam květin ze souboru kvetiny.txt.
        PlantManager mainManager = new PlantManager();
        readFromFile(mainManager, "resources/kvetiny.txt");
        mainManager.getPlantList().forEach(System.out::println);

        // Vypiš na obrazovku informace o zálivce pro všechny květiny ze seznamu.
        mainManager.getPlantList().forEach(plant -> {
            System.out.println(plant.getWateringInfo());
        });

        // Přidej novou květinu do seznamu (údaje si vymysli).
        Plant first = Plant.createPlant("Adenium obesum",
                "Adenium neboli pouštní růže je zcela nenáročná rostlina, která nádherně kvete.",
                6, LocalDate.of(2024, 10, 20),
                LocalDate.of(2024, 10, 16));
        mainManager.addPlant(first);
        mainManager.getPlantList().forEach(System.out::println);

        // Přidej 10 rostlin s popisem „Tulipán na prodej 1“ až „Tulipán na prodej 10“.
        // Zasazeny byly dnes, zality také, frekvence zálivky je 14 dnů.
        for(int i=0; i<10; i++){
            mainManager.addPlant(Plant.createPlant("Tulipán na prodej " + (i+1),
                    "Adenium neboli pouštní růže je zcela nenáročná rostlina, která nádherně kvete.",
                    14, LocalDate.now(), LocalDate.now()));
        }
//        mainManager.getPlantList().forEach(System.out::println);

        // Květinu na třetí pozici odeber ze seznamu (prodali jsme ji).
        mainManager.removePlantOnIndex(2);
//        mainManager.getPlantList().forEach(System.out::println);

        // Ulož seznam květin do nového souboru a ověř, že je jeho obsah odpovídá provedeným změnám.
        saveToFile(mainManager, "resources/test.txt");

        // Vyzkoušej opětovné načtení vygenerovaného souboru.
        PlantManager anotherManager = new PlantManager();
        readFromFile(anotherManager, "resources/test.txt");
        anotherManager.getPlantList().forEach(System.out::println);

        // Vyzkoušej seřazení rostlin ve správci seznamu podle různých kritérií a výpis seřazeného seznamu.
        System.out.println("Sorting by name:");
        anotherManager.sortPlantsByName();
        anotherManager.getPlantList().forEach(System.out::println);
        System.out.println("Sorting by last watering:");
        anotherManager.sortPlantsByWatering();
        anotherManager.getPlantList().forEach(System.out::println);

//        // My verification of the functionality of the application when I wrote the code.
//        PlantManager manager1 = new PlantManager();
//        Plant p1 = Plant.createPlant("Dendrobium nobile", "Dendrobium nobile is one of the most widespread ornamental members of the orchid family",
//                3, LocalDate.of(2024, 10, 18),
//                LocalDate.of(2024, 10, 15));
//        manager1.addPlant(p1);
//        if (p1 != null) {
//            System.out.println(p1.getWateringInfo());
//            System.out.println("Using the doWateringNow() method and then the info from the getWateringInfo() method");
//            p1.doWateringNow();
//            System.out.println(p1.getWateringInfo());
//        }
//
//        // Bad frequencyOfWatering
//        Plant p2 = Plant.createPlant("Phalaenopsis", -3);
//        manager1.addPlant(p2);
//
//        Plant p3 = Plant.createPlant("Cattleya");
//        manager1.addPlant(p3);
//        System.out.println("Just for control, have to be 2 plants:");
//        manager1.getPlantList().forEach(System.out::println);
//
//        // I did not add p4 in any PlantMananager
//        Plant p4 = Plant.createPlant("Oncidium");
//
//        Plant p5 = Plant.createPlant("Vanda", "These mostly epiphytic, but sometimes lithophytic or terrestrial orchids, are distributed in India, Himalaya..",
//                3, LocalDate.of(2024, 10, 18), LocalDate.of(2024, 10, 15));
//        manager1.addPlant(p5);
//        Plant p6 = Plant.createPlant("Brassia", "The genus was named after William Brass, a British botanist and illustrator",
//                2, LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 14));
//        manager1.addPlant(p6);
//
//
//        System.out.println("-*".repeat(20));
//        manager1.getPlantList().forEach(System.out::println);
//
//        System.out.println("-*".repeat(20));
//        System.out.println("Getting the first plant in managerList:");
//        System.out.println(manager1.getPlant(0));
//
//        System.out.println("-*".repeat(20));
//        System.out.println("Plants than need to watered:");
//        ArrayList<Plant> wateringList = manager1.needToWatering();
////        wateringList.forEach(plant -> System.out.println(plant));
//        wateringList.forEach(System.out::println);
//
//        System.out.println("-*".repeat(20));
//        System.out.println("Successful removal of p1 plant and unsuccessful removal of p4 plant");
//        manager1.removePlant(p1);
//        // I did not add p4 in any PlantMananager
//        manager1.removePlant(p4);
//
//        System.out.println("-*".repeat(20));
//        System.out.println("The list without sorting:");
//        manager1.getPlantList().forEach(System.out::println);
//        System.out.println("Sorting by name:");
//        manager1.sortPlantsByName();
//        manager1.getPlantList().forEach(System.out::println);
//        System.out.println("Sorting by last watering:");
//        manager1.sortPlantsByWatering();
//        manager1.getPlantList().forEach(System.out::println);
//
//        System.out.println("*-*-*- Task 6 Read from file with error in date -*-".repeat(2));
//        PlantManager manager = new PlantManager();
//        readFromFile(manager, FILENAME);
//        System.out.println("Just for control. Size of plantList: " + manager.getPlantList().size());
//        System.out.println("Continuation of the program");
//
//        readFromFile(manager, "resources/kvetiny-spatne-frekvence.txt");
//        System.out.println("Just for control. Size of plantList: " + manager.getPlantList().size());
//
//
//        System.out.println("Continuation of the program again");
//        System.out.println("SAVE TO FILE");
//        PlantManager man = new PlantManager();
//        readFromFile(man, "resources/flowers.txt");
//        Plant plant = Plant.createPlant("Lily");
//        man.addPlant(plant);
//        saveToFile(man, "resources/flowers.txt");


    }


}
