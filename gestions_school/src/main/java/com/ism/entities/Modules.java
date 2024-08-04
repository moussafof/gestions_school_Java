package com.ism.entities;

import java.util.List;
import lombok.*;
@Getter
@Setter
@ToString
public class Modules extends AbstractEntity {
  
    private String nom;
    private List<Classe> classes;
    private boolean isArchived;
   
    
    public Modules(int id, String nom) {
        super(id);
        this.nom = nom;
       
    }






    

    public Modules() {
    }


   
    

    
}
