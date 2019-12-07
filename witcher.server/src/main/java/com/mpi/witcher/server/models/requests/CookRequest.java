package com.mpi.witcher.server.models.requests;

import java.io.Serializable;

public class CookRequest implements Serializable {
    private long recipeId;

    public CookRequest() {}

    public CookRequest(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
