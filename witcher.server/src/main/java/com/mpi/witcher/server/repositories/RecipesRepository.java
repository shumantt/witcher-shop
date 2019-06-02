package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.Recipe;

import java.util.ArrayList;

public class RecipesRepository {
    public static ArrayList<Recipe> Recipes = new ArrayList<Recipe>() {
        {
            add(new Recipe("Поцелуй вампира", "Смертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe("Драконья гадость", "Несмертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe("Волкодав", "Смертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe("Терминатор", "Смертельные", "Семилистник, руна древних, глаз крокодила", true));
            add(new Recipe("Стероидобой", "Усиливающие", "Семилистник, руна древних, глаз крокодила", true));
        }
    };
}
