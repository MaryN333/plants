package cz.wz.marysidy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        Plant p1 = null;
        Plant p2 = null;
        Plant p3 = null;
        try {
            p1 = new Plant("Dendrobium nobile", "Dendrobium nobile is one of the most widespread ornamental members of the orchid family",
                    LocalDate.of(2024, 10, 15),
                    LocalDate.of(2024, 10, 10), 3);
            plants.add(p1);
            System.out.println(p1.getWateringInfo());
            p1.doWateringNow();
            System.out.println(p1.getWateringInfo());
        } catch (PlantException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try{
            p2 = new Plant("Phalaenopsis", -3);
            plants.add(p2);
        } catch (PlantException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            p3 = new Plant("Cattleya");
            plants.add(p3);
        } catch (PlantException e) {
            System.out.println("Error: " + e.getMessage());
        }

        plants.forEach(plant -> System.out.println(plant));
//        plants.forEach(System.out::println);

//        plants.forEach(plant -> {
//            if (plant != null) {
//                System.out.println(plant);
//            }
//        });


    }
}
