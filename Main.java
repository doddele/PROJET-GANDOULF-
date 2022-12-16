import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    /**
     * Lecture de l'équipement.
     */
    static void lectureEquipement() {
        try {
            // Fichier d'entrée.
            FileInputStream equipements = new FileInputStream(
                    "/home/jules/Desktop/PROJET GANDOULF/Ressources/equipements.txt");
            Scanner scanner = new Scanner(equipements);

            // Renvoie true s'il y a une autre ligne à lire.
            while (scanner.hasNextLine()) {

                // Prend une ligne du fichier texte à la fois et stocke la ligne dans "equipementLigne".
                String equipementLigne = scanner.nextLine();

                // Sépare les données de chaque perso et les stocke dans une liste.
                String[] equipementSplit = equipementLigne.split(";");

                // Ajoute chaque donnée à une variable.
                String a = equipementSplit[0];
                String b = equipementSplit[1];
                int c = Integer.parseInt(equipementSplit[2]);
                int d = Integer.parseInt(equipementSplit[3]);

                // Crée une arme ou une armure en fonction des données.
                if (Objects.equals(equipementSplit[0], "arme")) {
                    Arme test = new Arme(a, b, c, d);
                    System.out.println("type:" + a + " nom:" + b + " attaque:" + c + " prix:" + d);
                } else {
                    Armure test2 = new Armure(a, b, c, d);
                    System.out.println("type:" + a + " nom:" + b + " defense:" + c + " prix:" + d);
                }
            }

            // Ferme le scanner.
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lecture des personnages
     */
    static void lecturePersonnage() {
        System.out.println("lecture perso");
    }

    /**
     * Déroulement du jeu.
     */
    static void gameRun() {
        JFrame f = new JFrame();

        String nom = null;
        Personnage joueur = new Personnage(nom);
        // TODO: Sorcier joueur = new Sorcier(nom);
        Boolean gameRun = true;


        String[] introOptions = {"Créer un nouveau personnage", "Charger un personnage pré-existant"};
        int intro = JOptionPane.showOptionDialog(null, "Que voulez-vous faire ?",
                "Bienvenue dans Projet Gandoulf !", JOptionPane.DEFAULT_OPTION, -1,
                null, introOptions, introOptions[0]);

        loop:
        switch (intro) {
            case 0:     // Créer un nouveau personnage.
                String[] classeOptions = {"Barbare", "Sorcier"};
                int classe = JOptionPane.showOptionDialog(null, "Choisissez une classe.",
                        "Bienvenue dans Projet Gandoulf !", JOptionPane.DEFAULT_OPTION, -1,
                        null, classeOptions, classeOptions[0]);
                switch (classe) {

                    // Choix de la classe.
                    case 0:     // Création Barbare.
                        // Fais passer le joueur de Personnage à Barbare.
                        joueur = new Barbare(nom);

                        // Création d'un personnage barbare.
                        joueur.creerBarbare();

                        break loop;
                    case 1:     // Création Sorcier.

                        System.out.println("creation sorcier");
                        break;
                }
            case 1:     // Charger un personnage prééxistant.

                int choixPersoMenu = 0;
                try {
                    // Fichier d'entrée.
                    FileInputStream personnages = new FileInputStream(
                            "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
                    Scanner scanner = new Scanner(personnages);

                    // Liste qui va contenir les noms des personnages disponibles.
                    List<String> listeNomsPersos = new ArrayList<>();
                    List<String> listeStatPersos = new ArrayList<>();


                    // Renvoie true s'il y a une autre ligne à lire.
                    while (scanner.hasNextLine()) {
                        int compteur = 0;

                        // Prend une ligne du fichier texte à la fois et stocke la ligne dans "persoDispo".
                        String persoDispo = scanner.nextLine();

                        // Sépare les données de chaque perso et les stocke dans une liste.
                        String[] persosSplit = persoDispo.split(";");

                        // Ajoute le nom, la classe et le niveau du personnage dans l'arraylist "listeNomPersos".
                        listeNomsPersos.add(persosSplit[0] + " - " + persosSplit[1] + " niveau: " + persosSplit[2]);
                        listeStatPersos.add(persosSplit[compteur]);
                    }

                    // Transforme les arraylist en array.
                    String[] optionChoixPerso = listeNomsPersos.toArray(new String[0]);

                    choixPersoMenu = JOptionPane.showOptionDialog(null, "Choisissez un personnage:",
                            "Personnages", JOptionPane.DEFAULT_OPTION, -1, null, optionChoixPerso,
                            optionChoixPerso[0]);

                    scanner.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // String qui va comporter les statistiques du personnage.
                String statPerso = "";
                String[] statPersoSplit = {};

                // Lecture des stats du personnage choisi.
                try {
                    // Fichier d'entrée.
                    FileInputStream personnages = new FileInputStream(
                            "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
                    Scanner scanner = new Scanner(personnages);

                    // Indexe la ligne du personnage choisi
                    for (int i = 0; i <= choixPersoMenu; i++) {
                        if (i == choixPersoMenu) {

                            // Ajoute les statistiques du personnages a une string.
                            statPerso += scanner.nextLine();

                            // Split les statistiques du personnage.
                            statPersoSplit = statPerso.split(";");
                        }
                        if (scanner.hasNextLine()) {     // Passe à la ligne suivante s'il y en a une.
                            scanner.nextLine();
                        }
                    }
                    scanner.close();

                    // Choisi le constructeur du personnage en fonction de la classe.
                    switch (statPersoSplit[1]) {          // StatPersoSplit[1] correspond à la classe.
                        case "Barbare":
                            nom = statPersoSplit[0];

                            // Instancie un Barbare avec les statistiques du fichier txt.
                            joueur = new Barbare(nom,                       // nom
                                    Integer.parseInt(statPersoSplit[2]),    // niveau
                                    Integer.parseInt(statPersoSplit[3]),    // XP
                                    Integer.parseInt(statPersoSplit[4]),    // HPmax
                                    Integer.parseInt(statPersoSplit[5]),    // HP
                                    Integer.parseInt(statPersoSplit[6]),    // attaque
                                    Integer.parseInt(statPersoSplit[7]),    // defense
                                    Integer.parseInt(statPersoSplit[8]),    // esquive
                                    Double.parseDouble(statPersoSplit[9]),  // critique
                                    Integer.parseInt(statPersoSplit[10]),   // vitesse
                                    statPersoSplit[11],                     // arme1
                                    statPersoSplit[12],                     // arme2
                                    statPersoSplit[13],                     // armure
                                    Integer.parseInt(statPersoSplit[14]),   // or
                                    Integer.parseInt(statPersoSplit[15]));  // nbPotions
                            break;
                        case "Sorcier":
                            // TODO: création sorcier.
                            //joueur.lectureSorcier();
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        while (gameRun) {
            // Menu principal du jeu.
            String[] menuOptions = {"Combat de monstres", "Arène", "Combat entre joueurs", "Marchand",
                    "Sauvegarder"};
            int menu = JOptionPane.showOptionDialog(null, "\nQue voulez-vous faire ?",
                    joueur.nom + " - " + joueur.classe + " niveau: " + joueur.level,
                    JOptionPane.DEFAULT_OPTION, -1, null, menuOptions,
                    menuOptions[0]);
            switch (menu) {
                case 0:
                    // Combat de monstre.
                    break;
                case 1:
                    // Arène.
                    break;
                case 2:
                    // Combat entre joueurs.
                    break;
                case 3:

                    // Marchand.
                    String[] marchandOptions = {"Equipements", "Potions", "Vendre"};
                    int marchand = JOptionPane.showOptionDialog(null,
                            "Vous avez " + joueur.or + " pièces d'or." +
                                    "\nArme: " + joueur.arme + "\nArmure: " + joueur.armure + "\nNombre de potions: " +
                                    joueur.nbPotions + "\n\nQue voulez-vous acheter ?", "Marchand",
                            JOptionPane.DEFAULT_OPTION, -1, null, marchandOptions,
                            marchandOptions[0]);

                    switch (marchand) {
                        case 0:     // Equipements.
                            break;

                        case 1:     // Potions.
                            if (joueur.level < 10) {    // si niveau joueur <10, permet d'acheter une potion niveau 1.
                                Potion potion = new Potion(2, 100, 150);
                                potion.acheterPotions();
                            }

                            if(joueur.level < 20){
                                Potion potion = new Potion(2, 100, 150);

                                int nbPotionsAchetes = Integer.parseInt(JOptionPane.showInputDialog(f,
                                        "Vous avez " + joueur.or + " pièces d'or." +
                                                "\nCombien voulez-vous acheter de potions de niveau " + potion.niveau + " ?"
                                                + "\n\nPrix: " + potion.prix
                                                + "\nPV rendus: " + potion.soinPV));
                                if (joueur.or >= potion.prix * nbPotionsAchetes) {
                                    joueur.setOr(joueur.or - potion.prix * nbPotionsAchetes);
                                    joueur.setNbPotions(joueur.nbPotions + nbPotionsAchetes);
                                } else {
                                    JOptionPane.showMessageDialog(f, "Vous n'avez pas assez d'argent pour acheter cet article!",
                                            "Marchand", -1);
                                }
                                break;
                            }

                            if(joueur.level < 30){
                                Potion potion = new Potion(3, 150, 250);

                                int nbPotionsAchetes = Integer.parseInt(JOptionPane.showInputDialog(f,
                                        "Vous avez " + joueur.or + " pièces d'or." +
                                                "\nCombien voulez-vous acheter de potions de niveau " + potion.niveau + " ?"
                                                + "\n\nPrix: " + potion.prix
                                                + "\nPV rendus: " + potion.soinPV));
                                if (joueur.or >= potion.prix * nbPotionsAchetes) {
                                    joueur.setOr(joueur.or - potion.prix * nbPotionsAchetes);
                                    joueur.setNbPotions(joueur.nbPotions + nbPotionsAchetes);
                                } else {
                                    JOptionPane.showMessageDialog(f, "Vous n'avez pas assez d'argent pour acheter cet article!",
                                            "Marchand", -1);
                                }
                                break;
                            }
                            break;

                        case 2:     // Vendre de l'équipement ou des potions.
                            break;
                    }
                    break;
                case 4:

                    // Sauvegarde.
                    // TODO: Sauvegarder sur ligne où le perso est déjà enregistré.
                    joueur.save();
                    JOptionPane.showMessageDialog(f, "Partie sauvegardée!");
                    break;
            }

        }
        // gameRun = false; A AJOUTER quand on quitte la fenêtre
    }


    /**
     * Méthode main.
     */
    public static void main(String[] args) {
        gameRun();
    }
}