package com.ism.entities;
import lombok.*;
@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class AbstractEntity {
    protected int id;

    public AbstractEntity(int id) {
        
        this.id = id;
    }

    public AbstractEntity() {
    }
    
}
