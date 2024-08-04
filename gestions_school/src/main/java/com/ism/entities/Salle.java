package com.ism.entities;
import lombok.*;
@Getter
@Setter
@ToString
public class Salle extends AbstractEntity {
    
    private int numero;
    private int capacite;
    private boolean isArchived;

    public Salle(int id, int numero, int capacite, boolean isArchived) {
        super(id);
        this.numero = numero;
        this.capacite = capacite;
        this.isArchived = isArchived;
    }

    public Salle(int numero, int capacite, boolean isArchived) {
        this.numero = numero;
        this.capacite = capacite;
        this.isArchived = isArchived;
    }

    public Salle(int numero, int capacite) {
        this.numero = numero;
        this.capacite = capacite;
    }


    public Salle() {
    }
    
   
    
}
