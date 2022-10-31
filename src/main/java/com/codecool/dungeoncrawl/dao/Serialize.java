package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.entities.Entity;

public interface Serialize {
    void serialize(Object object);
    Object getFromJSON();
}
