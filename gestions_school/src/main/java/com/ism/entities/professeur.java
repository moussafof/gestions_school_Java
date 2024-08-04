package com.ism.entities;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Getter
@Setter
@ToString
public class professeur extends AbstractEntity {

    private String nom;
    private List<Modules> modulesEnseignes = new ArrayList<>();
    private List<Classe> classesEnseignees = new ArrayList<>();
    private boolean isArchived;
    private int numero_tel;
    
   
    public professeur(int id, String nom,  int numero_tel) {
        super(id);
        this.nom = nom;
        this.numero_tel = numero_tel;
    }


    public professeur(int id, String nom, boolean isArchived) {
        super(id);
        this.nom = nom;
        this.isArchived = isArchived;
    }


    public professeur(String nom, List<Modules> modulesEnseignes, List<Classe> classesEnseignees, boolean isArchived) {
        this.nom = nom;
        this.modulesEnseignes = modulesEnseignes;
        this.classesEnseignees = classesEnseignees;
        this.isArchived = isArchived;
    }


    public professeur(int id, String nom, List<Modules> modulesEnseignes, List<Classe> classesEnseignees) {
        super(id);
        this.nom = nom;
        this.modulesEnseignes = modulesEnseignes;
        this.classesEnseignees = classesEnseignees;
    }


    public professeur() {
    }


    
}

