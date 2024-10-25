package cz.wz.marysidy;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PlantManager manager1 = new PlantManager();
        Plant p1 = Plant.createPlant("Dendrobium nobile", "Dendrobium nobile is one of the most widespread ornamental members of the orchid family",
                LocalDate.of(2024, 10, 15),
                LocalDate.of(2024, 10, 18), 3);
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
                LocalDate.of(2024, 10, 15),
                LocalDate.of(2024, 10, 18), 3);
        manager1.addPlant(p5);
        Plant p6 = Plant.createPlant("Brassia", "The genus was named after William Brass, a British botanist and illustrator",
                LocalDate.of(2024, 10, 14),
                LocalDate.of(2024, 10, 15), 2);
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














//        try {
//            Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/cz/wz/marysidy/info.csv")));
//            while (scanner.hasNextLine()) {
//                String record = scanner.nextLine();
//                System.out.println(record);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading from file: " + e.getLocalizedMessage());
//        }
    }
}
