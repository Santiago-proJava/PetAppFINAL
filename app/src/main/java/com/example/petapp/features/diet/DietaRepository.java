package com.example.petapp.features.diet;

import java.util.ArrayList;
import java.util.List;

public class DietaRepository {
    private static final List<Dieta> dietas = new ArrayList<>();
    private static int nextId = 1;

    public static List<Dieta> getAllDietas() {
        return new ArrayList<>(dietas);
    }

    public static void addDieta(Dieta dieta) {
        dieta.setId(nextId++);
        dietas.add(dieta);
    }

    public static void updateDieta(Dieta dietaActualizada) {
        for (int i = 0; i < dietas.size(); i++) {
            if (dietas.get(i).getId() == dietaActualizada.getId()) {
                dietas.set(i, dietaActualizada);
                break;
            }
        }
    }

    public static void deleteDieta(int id) {
        for (int i = 0; i < dietas.size(); i++) {
            if (dietas.get(i).getId() == id) {
                dietas.remove(i);
                break;
            }
        }
    }
}
