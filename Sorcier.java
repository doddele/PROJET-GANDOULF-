import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Sorcier extends Personnage {
    private int attaque;
    private int mana;
    private int manaMax;
    private double malusEquipement;
    private String sorts;
    public Sorcier(String nom) {
        super(nom);
        this.attaque = 5;
        this.mana = 50;
        this.manaMax = 50;
        this.malusEquipement = 10;
        this.sorts = "/////////";   // String qui va contenir la liste des sorts du sorcier. (ex: Boule de feu/Eclair/.../)
        this.classe = "Sorcier";
    }
    public Sorcier(String nom, int level, int XP, int HPmax, int mana, int manaMax, int HP, int attaque, int defense, int esquive,
                   int vitesse, String arme, String armure, int or, int nbPotions, String sorts) {
        super(nom);
        this.level = level;
        this.XP = XP;
        this.HPmax = HPmax;
        this.HP = HP;
        this.manaMax = manaMax;
        this.mana = mana;
        this.attaque = attaque;
        this.defense = defense;
        this.esquive = esquive;
        this.vitesse = vitesse;
        this.arme = arme;
        this.armure = armure;
        this.or = or;
        this.nbPotions = nbPotions;
        this.classe = "Sorcier";
        this.sorts = sorts;
        this.malusEquipement = 10;
    }
    @Override
    public void save() {

        try {
            // Ecrit les statistiques du personnage dans une nouvelle ligne du fichier
            File fichierPerso = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
            FileWriter myWriter = new FileWriter(
                    "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt", true);
            System.out.println(nom);
            myWriter.write(this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                    + ";" + this.HP + ";" + this.manaMax + ";" + this.mana +  ";" + this.attaque + ";" + this.defense
                    + ";" + this.esquive + ";" + this.vitesse + ";" + this.arme + ";" + this.armure + ";" + this.or
                    + ";" + this.nbPotions + ";" + this.sorts + "\n");
            myWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getStat(){
        return(this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                + ";" + this.HP + ";" + this.manaMax + ";" + this.mana +  ";" + this.attaque + ";" + this.defense
                + ";" + this.esquive + ";" + this.vitesse + ";" + this.arme + ";" + this.armure + ";" + this.or
                + ";" + this.nbPotions + ";" + this.sorts + "\n");
    }

    // TODO: méthode bouleDeFeu

    // TODO: méthode gainXP(int xpGagnée)

    // TODO: méthode gainNiveau
}
