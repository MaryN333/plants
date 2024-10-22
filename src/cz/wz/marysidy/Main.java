package cz.wz.marysidy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Plant p1 = new Plant("Dendrobium nobile", "Dendrobium nobile is one of the most widespread ornamental members of the orchid family", LocalDate.of(2024, 10, 20), LocalDate.of(2024,10, 23), 3);
        Plant p2 = new Plant("Phalaenopsis", 3);
        Plant p3 = new Plant("Cattleya");

        List<Plant> plants = new ArrayList<>();
        plants.add(p1);
        plants.add(p2);
        plants.add(p3);
        plants.forEach(plant -> System.out.println(plant));


    }
}
