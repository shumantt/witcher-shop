package com.mpi.witcher.server.models.requests;

import com.mpi.witcher.server.models.Recipe;

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
    private ArrayList<Recipe.Component> ingredients;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public ArrayList<Recipe.Component> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Recipe.Component> ingredients) {
        this.ingredients = ingredients;
    }
}
