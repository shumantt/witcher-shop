package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.Recipe;

import java.util.ArrayList;

public class RecipesRepository {
    public static ArrayList<Recipe> Recipes = new ArrayList<Recipe>() {
        {
            add(new Recipe(1, "Поцелуй вампира", "Смертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe(2, "Драконья гадость", "Несмертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe(3, "Волкодав", "Смертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe(4, "Терминатор", "Смертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe(5, "Стероидобой", "Усиливающие", "Семилистник, руна древних, глаз крокодила", true));
        }
    };
}
