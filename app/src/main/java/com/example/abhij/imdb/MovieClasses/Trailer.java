package com.example.abhij.imdb.MovieClasses;

import java.io.Serializable;

/**
 * Created by abhij on 17-04-2018.
 */

public class Trailer implements Serializable{


    public String id ;
    public String name;
    public String key;
    public int size;
    public String type;

    public Trailer(String id, String name, String key, int size, String type) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.size = size;
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey(){return key;}

    public void setKey(String key){ this.key= key;}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
