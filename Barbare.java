import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Barbare extends Personnage {
    private String arme2;
    private int attaque;
    private double critique;
    public Barbare(String nom) {
        super(nom);
        this.arme2 = "vide";
        this.attaque = 10;
        this.critique = 0.5;
        this.esquive = 10;
        this.HPmax = 150;
        this.HP = 150;
        this.classe = "Barbare";
    }
    public Barbare(String nom, int level, int XP, int HPmax, int HP, int attaque, int defense, int esquive,
                   double critique, int vitesse, String arme, String arme2, String armure, int or, int nbPotions) {
        super(nom);
        this.level = level;
        this.XP = XP;
        this.HPmax = HPmax;
        this.HP = HP;
        this.attaque = attaque;
        this.defense = defense;
        this.esquive = esquive;
        this.critique = critique;
        this.vitesse = vitesse;
        this.arme = arme;
        this.arme2 = arme2;
        this.armure = armure;
        this.or = or;
        this.nbPotions = nbPotions;
        this.classe = "Barbare";
    }
    @Override
    public String getStat(){
        return(this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                + ";" + this.HP + ";" + this.attaque + ";" + this.defense + ";" + this.esquive + ";"
                + this.critique + ";" + this.vitesse + ";" + this.arme + ";" + this.arme2 + ";" + this.armure
                + ";" + this.or + ";" + this.nbPotions + "\n");
    }

    /**
     * Sauvegarde un barbare dans le fichier "personnages.txt".
     */
    @Override
    public void save() {
        try {
            // Ecrit les statistiques du personnage dans une nouvelle ligne du fichier
            File fichierPerso = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
            FileWriter myWriter = new FileWriter(
                    "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt", true);
            System.out.println(nom);
            myWriter.write(this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                    + ";" + this.HP + ";" + this.attaque + ";" + this.defense + ";" + this.esquive + ";"
                    + this.critique + ";" + this.vitesse + ";" + this.arme + ";" + this.arme2 + ";" + this.armure
                    + ";" + this.or + ";" + this.nbPotions + "\n");
            myWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: méthode gainXP(int expGagnée)

    // TODO: méthode gainNiveau
}