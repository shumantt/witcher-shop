package com.mpi.witcher.server.models.requests;

import java.util.ArrayList;
/*
* {
*   name: "Убийца духов",
    description: "Убивает духов",
    instruction: "Жарить, пока не отклеются обои",
    ingredients: [
    * {
    *   type: "grass", //(+animals, runes)
        id: 1, //ид ресурса - травы, животного или руны
        quantity: 5
    * }
    * ]
* }
*
* */
public class AddProducableItemRequest extends AddItemRequest {
    private String instruction;
    private ArrayList<Component> ingredients;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public ArrayList<Component> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Component> ingredients) {
        this.ingredients = ingredients;
    }

    public class Component {
        private String type;
        private int id;
        private int quantity;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
