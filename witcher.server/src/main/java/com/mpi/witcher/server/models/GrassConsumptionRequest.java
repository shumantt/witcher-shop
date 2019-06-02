package com.mpi.witcher.server.models;

import java.io.Serializable;

public class GrassConsumptionRequest implements Serializable {
    private long id;
    private int reduceBy;

    public GrassConsumptionRequest(long id, int reduceBy) {
        this.id = id;
        this.reduceBy = reduceBy;
    }

    public GrassConsumptionRequest() {}

    public int getReduceBy() {
        return reduceBy;
    }

    public void setReduceBy(int reduceBy) {
        this.reduceBy = reduceBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
