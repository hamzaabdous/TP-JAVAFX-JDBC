package ma.enset.untiled4.dao.entities;

import java.util.List;

public class Category {
    private long id;
    private String name;
    public Category() {
    }

    public Category( String name) {
        this.name = name;
    }

    public Category(long id, String name) {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return  name ;
    }
}
