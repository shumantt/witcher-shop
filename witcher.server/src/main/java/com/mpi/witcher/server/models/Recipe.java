package com.mpi.witcher.server.models;

import java.util.List;

public class Recipe extends Product {

    private String instruction;
    private List<Component> components;
    private boolean enoughResources;

    public Recipe(int id, String name, String description, List<String> categories, int quantity, String instruction, List<Component> components, boolean enoughResources) {
        super(id, name, description, categories, quantity);
        this.instruction = instruction;
        this.components = components;
        this.enoughResources = enoughResources;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getComponents() {
        StringBuilder formattedComponents = new StringBuilder();
        for(Recipe.Component c : components) {
            formattedComponents.append(c.getName()).append(" (x").append(c.getRequiredQuantity()).append("), ");
        }
        formattedComponents.delete(formattedComponents.length() - 2, formattedComponents.length());
        return formattedComponents.toString();
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public boolean isEnoughResources() {
        return enoughResources;
    }

    public void setEnoughResources(boolean enoughResources) {
        this.enoughResources = enoughResources;
    }

    public static class Component {
        private int productId;
        private int requiredQuantity;
        private String name;

        public Component(int productId, int requiredQuantity, String name) {
            this.productId = productId;
            this.requiredQuantity = requiredQuantity;
            this.name = name;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getRequiredQuantity() {
            return requiredQuantity;
        }

        public void setRequiredQuantity(int requiredQuantity) {
            this.requiredQuantity = requiredQuantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
