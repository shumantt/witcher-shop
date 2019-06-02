package com.mpi.witcher.server.models;

public class Recipe {
    private String name;
    private String category;
    private String components;
    private boolean enoughResources;


    public Recipe(String name, String category, String components, boolean enoughResources) {
        this.name = name;
        this.category = category;
        this.components = components;
        this.enoughResources = enoughResources;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getComponents() {
        return components;
    }

    public boolean isEnoughResources() {
        return enoughResources;
    }
}
