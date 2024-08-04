package com.ism;

import java.util.ArrayList;
import java.util.Scanner;

import com.ism.entities.Classe;
import com.ism.entities.Modules;
import com.ism.entities.Salle;
import com.ism.entities.professeur;
import com.ism.repositories.core.impl.*;
import com.ism.services.classeService;
import com.ism.services.moduleService;
import com.ism.services.professeurService;
import com.ism.services.salleService;
import com.ism.services.impl.*;
public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
           
            System.out.println("Menu Principal");
            System.out.println("1- Gérer Classe");
            System.out.println("2- Gérer Modules");
            System.out.println("3- Gérer Prof");
            System.out.println("4- Gérer Salle");
            System.out.println("5- Planifier un cours");
            System.out.println("6-Affecter les Modules à une Classe ");
            System.out.println("7- afficher les Modules d'une Classe");
            System.out.println("8- afficher les Classes d'un Module");
            System.out.println("9- afficher les Classes et Modules à un Prof");
            System.out.println("10- afficher les Cours d'une Classe");
            System.out.println("11- afficher les Cours d'un prof");
            System.out.println("12- Quitter");
            System.out.print("Choisissez une option : ");
            
        while (true) {
            
            int choixPrincipal = scanner.nextInt();

            switch (choixPrincipal) {
                
                case 1:
                    clearConsole();
                    gererClasses();
                    break;
                case 2:
                    clearConsole();
                    gererModules();
                    break;
                case 3:
                    clearConsole();
                    gererProf();
                    break;
                case 4:
                    clearConsole();
                    gererSalle();
                    break;
                // case 5:
                //     planifierCours();
                //     break;
                case 6:
                    affecterModulesClasse();
                    break;
                case 7:
                    afficherModulesClasse();
                    break;
                case 8:
                    afficherClassesModule();
                    break;
                case 9:
                    afficherClassesModulesProf();
                    break;
                case 10:
                    afficherCoursClasse();
                    break;
                case 11:
                    afficherCoursProf();
                    break;
                case 12:
                    System.out.println("Au revoir!");
                    System.exit(0);
                   
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
           
        }
        
    }
    
    private static void gererProf() {
    

    System.out.println("1-Ajouter un Professeur");
    System.out.println("2- Modifier un Professeur");
    System.out.println("3- Lister les Professeurs");
    System.out.println("4- Archiver un Professeur");
    System.out.println("5- Retour");
    System.out.println("Faites votre choix");

    int choixProf = scanner.nextInt();

    while (choixProf != 5) {
        professeurService profservice = new profServiceImpl(new database());
        switch (choixProf) {

            case 1:
            System.out.println("Quel est le numéro du prof ?");
            int numero = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne restant

            System.out.println("Quel est le nom du prof ?");
            String nom = scanner.nextLine();
           
             professeur newprof = new professeur();
             newprof.setNom(nom);
             newprof.setNumero_tel(numero);
             
             // Appeler la méthode add du service pour ajouter la salle
             int resultat = profservice.add(newprof);
             
             if (resultat > 0) {
                 System.out.println("Prof ajouté avec succès !");
                
             } else {
                 System.out.println("Échec de l'ajout du prof");
             }
             break;

            case 2:
                System.out.println("Quel est l'ID du prof à modifier ?");
                int idProfModifier = scanner.nextInt();

            // Récupérer la salle par son ID
            professeur ProfAModifier = profservice.findByID(idProfModifier);

            if (ProfAModifier != null) {
                System.out.println("Quel est le nouveau nom du prof ?");
                ProfAModifier.setNom(scanner.nextLine());

                System.out.println("Quelle est le numéro de telephone ?");
                ProfAModifier.setNumero_tel(scanner.nextInt());

                // Appeler la méthode update du service pour modifier la salle
                int resultatUpdate = profservice.update(ProfAModifier);

                if (resultatUpdate > 0) {
                    System.out.println("Prof modifié avec succès !");
                    
                } else {
                    System.out.println("Échec de la modification du prof");
                }
            } else {
                System.out.println("Le prof avec l'ID spécifié n'existe pas.");
            }
            
             break;


            case 3:
               ArrayList<professeur> profs = profservice.findAll();
            for (professeur prof : profs) {
                System.out.println(prof);   
            }
            return;

            case 4:
            System.out.println("Quel est l'ID du prof à archiver ?");
            int idProfArchiver = scanner.nextInt();

            // Appeler la méthode archive du service pour archiver la salle
            int resultatArchiver = profservice.archive(idProfArchiver);

            if (resultatArchiver > 0) {
                System.out.println("Salle archivée avec succès !");
            } else {
                System.out.println("Échec de l'archivage de la salle.");
            }
            break;


            default:
                System.out.println("Choix invalide.");
                break;
        }

        System.out.println("Faites votre choix");
        choixProf = scanner.nextInt();
    }
}

private static void gererClasses() {
    classeService classeService = new classeServiceImpl(new database());

    System.out.println("1-Ajouter une Classe");
    System.out.println("2- Modifier une Classe");
    System.out.println("3- Lister les Classes");
    System.out.println("4- Archiver une Classe");
    System.out.println("5- Retour");
    System.out.println("Faites votre choix");

    int choixClasse = scanner.nextInt();

    while (choixClasse != 5) {
        switch (choixClasse) {
            case 1:
                // Ajouter une classe
                System.out.println("Entrez le nom de la classe : ");
                String nomClasse = scanner.next();
                System.out.println("Entrez le nombre d'élèves de la classe : ");
                int nbreEleve = scanner.nextInt();

                Classe nouvelleClasse = new Classe();
                nouvelleClasse.setNom(nomClasse);
                nouvelleClasse.setNbreEleve(nbreEleve);
                int resultatAjoutClasse = classeService.add(nouvelleClasse);

                if (resultatAjoutClasse > 0) {
                    System.out.println("Classe ajoutée avec succès !");
                } else {
                    System.out.println("Échec de l'ajout de la classe.");
                }
                break;

            case 2:
                // Modifier une classe
                System.out.println("Entrez l'ID de la classe à modifier : ");
                int idClasseModifier = scanner.nextInt();

                Classe classeAModifier = classeService.findByID(idClasseModifier);

                if (classeAModifier != null) {
                    System.out.println("Entrez le nouveau nom de la classe : ");
                    String nouveauNomClasse = scanner.next();
                    System.out.println("Entrez le nombre d'élèves de la classe : ");
                    int nbre_Eleve = scanner.nextInt();

                    classeAModifier.setNom(nouveauNomClasse);
                    classeAModifier.setNbreEleve(nbre_Eleve);
                    int resultatUpdateClasse = classeService.update(classeAModifier);

                    if (resultatUpdateClasse > 0) {
                        System.out.println("Classe modifiée avec succès !");
                    } else {
                        System.out.println("Échec de la modification de la classe.");
                    }
                } else {
                    System.out.println("La classe avec l'ID spécifié n'existe pas.");
                }
                break;

            case 3:
                // Lister toutes les classes
                ArrayList<Classe> classes = classeService.findAll();
                for (Classe classe : classes) {
                    System.out.println(classe);
                }
                break;

            case 4:
                // Archiver une classe
                System.out.println("Entrez l'ID de la classe à archiver : ");
                int idClasseArchiver = scanner.nextInt();

                int resultatArchiverClasse = classeService.archive(idClasseArchiver);

                if (resultatArchiverClasse > 0) {
                    System.out.println("Classe archivée avec succès !");
                } else {
                    System.out.println("Échec de l'archivage de la classe.");
                }
                break;

            default:
                System.out.println("Choix invalide.");
                break;
        }

        System.out.println("Faites votre choix");
        choixClasse = scanner.nextInt();
    }
}

private static void gererModules() {
    moduleService moduleService = new moduleServiceImpl(new database());

    System.out.println("1-Ajouter un Module");
    System.out.println("2- Modifier un Module");
    System.out.println("3- Lister les Modules");
    System.out.println("4- Archiver un Module");
    System.out.println("5- Retour");
    System.out.println("Faites votre choix");

    int choixModule = scanner.nextInt();

    while (choixModule != 5) {
        switch (choixModule) {
            case 1:
                // Ajouter un module
                System.out.println("Entrez le nom du module : ");
                String nomModule = scanner.next();

                Modules nouveauModule = new Modules();
                nouveauModule.setNom(nomModule);

                int resultatAjoutModule = moduleService.add(nouveauModule);

                if (resultatAjoutModule > 0) {
                    System.out.println("Module ajouté avec succès !");
                } else {
                    System.out.println("Échec de l'ajout du module.");
                }
                break;

            case 2:
                // Modifier un module
                System.out.println("Entrez l'ID du module à modifier : ");
                int idModuleModifier = scanner.nextInt();

                Modules moduleAModifier = moduleService.findByID(idModuleModifier);

                if (moduleAModifier != null) {
                    System.out.println("Entrez le nouveau nom du module : ");
                    String nouveauNomModule = scanner.next();

                    moduleAModifier.setNom(nouveauNomModule);

                    int resultatUpdateModule = moduleService.update(moduleAModifier);

                    if (resultatUpdateModule > 0) {
                        System.out.println("Module modifié avec succès !");
                    } else {
                        System.out.println("Échec de la modification du module.");
                    }
                } else {
                    System.out.println("Le module avec l'ID spécifié n'existe pas.");
                }
                break;

            case 3:
                // Lister tous les modules
                ArrayList<Modules> modules = moduleService.findAll();
                for (Modules module : modules) {
                    System.out.println(module);
                }
                break;

            case 4:
                // Archiver un module
                System.out.println("Entrez l'ID du module à archiver : ");
                int idModuleArchiver = scanner.nextInt();

                int resultatArchiverModule = moduleService.archive(idModuleArchiver);

                if (resultatArchiverModule > 0) {
                    System.out.println("Module archivé avec succès !");
                } else {
                    System.out.println("Échec de l'archivage du module.");
                }
                break;

            default:
                System.out.println("Choix invalide.");
                break;
        }

        System.out.println("Faites votre choix");
        choixModule = scanner.nextInt();
    }
}

    // Les autres méthodes restent inchangées, vous pouvez les implémenter de manière similaire.
     private  static void gererSalle() {
        
        System.out.println("1-Ajouter une Salle");
        System.out.println("2- Modifier une Salle");
        System.out.println("3- Lister une Salle");
        System.out.println("4- Archiver une Salle");
        System.out.println("5- Retour");
        System.out.println("Faites votre choix");
        int choixSalle = scanner.nextInt();
        while (choixSalle!=5) {
            
            salleService SalleService = new salleServiceImpl(new database());
            switch (choixSalle) {
             case 1:
             System.out.println("Quel est le numéro de la salle ?");
             int numero = scanner.nextInt();
             
             System.out.println("Quelle est la capacité de la salle ?");
             int capacite = scanner.nextInt();
             
             // Créer une nouvelle salle
             Salle nouvelleSalle = new Salle();
             nouvelleSalle.setNumero(numero);
             nouvelleSalle.setCapacite(capacite);
             
             // Appeler la méthode add du service pour ajouter la salle
             int resultat = SalleService.add(nouvelleSalle);
             
             if (resultat > 0) {
                 System.out.println("Salle ajoutée avec succès !");
                
             } else {
                 System.out.println("Échec de l'ajout de la salle.");
             }
             break;

            case 2:
            System.out.println("Quel est l'ID de la salle à modifier ?");
            int idSalleModifier = scanner.nextInt();

            // Récupérer la salle par son ID
            Salle salleAModifier = SalleService.findByID(idSalleModifier);

            if (salleAModifier != null) {
                System.out.println("Quel est le nouveau numéro de la salle ?");
                salleAModifier.setNumero(scanner.nextInt());

                System.out.println("Quelle est la nouvelle capacité de la salle ?");
                salleAModifier.setCapacite(scanner.nextInt());

                // Appeler la méthode update du service pour modifier la salle
                int resultatUpdate = SalleService.update(salleAModifier);

                if (resultatUpdate > 0) {
                    System.out.println("Salle modifiée avec succès !");
                    
                } else {
                    System.out.println("Échec de la modification de la salle.");
                }
            } else {
                System.out.println("La salle avec l'ID spécifié n'existe pas.");
            }
            
             break;

        case 3:
            // Appeler la méthode list du service pour afficher toutes les salles
            ArrayList<Salle> salles = SalleService.findAll();
            for (Salle salle : salles) {
                System.out.println(salle);   
            }
            return;
            
            
        case 4:
            System.out.println("Quel est l'ID de la salle à archiver ?");
            int idSalleArchiver = scanner.nextInt();

            // Appeler la méthode archive du service pour archiver la salle
            int resultatArchiver = SalleService.archive(idSalleArchiver);

            if (resultatArchiver > 0) {
                System.out.println("Salle archivée avec succès !");
            } else {
                System.out.println("Échec de l'archivage de la salle.");
            }
            break;

        
        default:
            System.out.println("Choix invalide.");
            break;
        } 
          
       choixSalle = scanner.nextInt();
       
       
    }
    
}
    private static void affecterModulesClasse() {
        // Logique pour affecter des modules à une classe
        System.out.println("Option non implémentée");
    }

    private static void afficherModulesClasse() {
        // Logique pour afficher les modules d'une classe
        System.out.println("Option non implémentée");
    }

    private static void afficherClassesModule() {
        // Logique pour afficher les classes qui font un module
        System.out.println("Option non implémentée");
    }

    private static void afficherClassesModulesProf() {
        // Logique pour afficher les classes et modules d'un professeur
        System.out.println("Option non implémentée");
    }

    private static void afficherCoursClasse() {
        // Logique pour afficher les cours d'une classe
        System.out.println("Option non implémentée");
    }

    private static void afficherCoursProf() {
        // Logique pour afficher les cours d'un professeur
        System.out.println("Option non implémentée");
    }

   private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Pour Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Pour Linux/Unix et MacOS
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // Gérer les exceptions
            e.printStackTrace();
        }
        
    }

} 



