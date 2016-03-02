package com.netteans.ring.band;

/**
 * Created by Kings on 2016/3/2.
 */

public class Band {
    private final long id;

    private Band(long id) {
        this.id = id;
    }

    public Band tie(){
        return this;
    }
}
