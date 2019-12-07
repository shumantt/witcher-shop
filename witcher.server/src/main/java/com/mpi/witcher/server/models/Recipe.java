package com.mpi.witcher.server.models;

public class Recipe {
    private long id;
    private String name;
    private String category;
    private String components;
    private boolean enoughResources;
    private int potionQuantity = 5; // TODO инфа о количества зелий по рецепту на складе (нужна для фронта)

    //TODO добавить инфу о кличестве зелий на складе, приготовленных по этому рецепту
    public Recipe(long id, String name, String category, String components, boolean enoughResources) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPotionQuantity() {
        return potionQuantity;
    }

    public void setPotionQuantity(int potionQuantity) {
        this.potionQuantity = potionQuantity;
    }
}
