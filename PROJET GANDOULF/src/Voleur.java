import jdk.jfr.Percentage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Voleur extends Personnage{
    public Voleur(String nom){
        super(nom);
        this.attaque = this.vitesse;
        this.critique = 0.5;
        this.esquive = 10;
        this.HPmax = 100;
        this.HP = 100;
        this.classe = "Voleur";
    }
    public Voleur(String nom, int level, int XP, int HPmax, int HP, int attaque, int defense, double esquive,
                   double critique, int vitesse, String arme, String armure, int or, int nbPotions) {
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
        this.armure = armure;
        this.or = or;
        this.nbPotions = nbPotions;
        this.classe = "Voleur";
    }
    @Override
    public String getStat(){
        return(this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                + ";" + this.HP + ";" + this.attaque + ";" + this.defense + ";" + this.esquive + ";"
                + this.critique + ";" + this.vitesse + ";" + this.arme + ";" + this.armure
                + ";" + this.or + ";" + this.nbPotions + "\n");
    }
    /**
     * Sauvegarde un vooleur dans le fichier "personnages.txt".
     */
    @Override
    public void save() {
        try {
            // Ecrit les statistiques du personnage dans une nouvelle ligne du fichier
            FileWriter myWriter = new FileWriter(
                    "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt", true);
            System.out.println(nom);
            System.out.println(this.arme);
            myWriter.write(this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                    + ";" + this.HP + ";" + this.attaque + ";" + this.defense + ";" + this.esquive + ";"
                    + this.critique + ";" + this.vitesse + ";" + this.arme + ";" + this.armure
                    + ";" + this.or + ";" + this.nbPotions + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void gainNiveau() {
        JOptionPane.showMessageDialog(null, this.nom + " passe du niveau " + this.level + " au niveau " + (this.level+1) + " !\n" +
                "\nHP: " + this.HPmax + " -> " + (this.HPmax + 10) +
                "\nVIT: " + this.vitesse + " -> " + (this.vitesse + 5) +
                "\nCRIT: " + this.critique + " -> " + (this.critique + 0.5) +
                "\nESQ: " + this.esquive + " -> " + (this.esquive + 0.5));
        this.level += 1;
        this.HPmax += 10;
        this.HP = this.HPmax;
        this.vitesse += 5;
        this.critique += 0.5;
        this.esquive += 0.5;
    }
}
