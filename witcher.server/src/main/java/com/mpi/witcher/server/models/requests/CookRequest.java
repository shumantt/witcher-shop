package com.mpi.witcher.server.models.requests;

import java.io.Serializable;

public class CookRequest implements Serializable {
    private int recipeId;

    public CookRequest() {}

    public CookRequest(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
