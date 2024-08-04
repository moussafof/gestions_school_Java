package com.ism.entities;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Getter
@Setter
public class Classe extends AbstractEntity {
    private String nom;
    private int nbreEleve;
    private List<professeur> profs = new ArrayList<>(); 
    private List<Modules> modules = new ArrayList<>();
    private boolean isArchived;
    public Classe(int id, String nom, int nbreEleve) {
        super(id);
        this.nom = nom;
        this.nbreEleve = nbreEleve;
    }
    public Classe(String nom, int nbreEleve, List<professeur> profs, List<Modules> modules, boolean isArchived) {
        this.nom = nom;
        this.nbreEleve = nbreEleve;
        this.profs = profs;
        this.modules = modules;
        this.isArchived = isArchived;
    }
    public Classe() {
    }
    public Classe(int id, String nom, int nbreEleve, List<professeur> profs, List<Modules> modules) {
        super(id);
        this.nom = nom;
        this.nbreEleve = nbreEleve;
        this.profs = profs;
        this.modules = modules;
    }
    @Override
    public String toString() {
        return "Classe [nom=" + nom + ", nbreEleve=" + nbreEleve + ", profs=" + profs + ", modules=" + modules + "]";
    }
    
    


    
}
