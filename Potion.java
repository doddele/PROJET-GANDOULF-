import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Potion implements Marchand {
    public String nom = "Potion";
    public int niveau;
    public int prix;
    public int soinPV;
    public Potion(int niveau, int prix, int soinPV){
        this.niveau = niveau;
        this.prix = prix;
        this.soinPV = soinPV;
    }

    /**
     * Méthode permettant d'instancier les potions correspondant au niveau du personnage chez le marchand.
     * @param joueur
     */
    public static void acheterPotion(Personnage joueur){
        if (joueur.level < 10) {    // si niveau joueur <10, permet d'acheter une potion niveau 1.
            Potion potion = new Potion(1, 50, 100);
            potion.acheterPotions(joueur); // On appelle la méthode "acheterPotions" de la classe Potion.
        }
        if (joueur.level > 10 && joueur.level < 20) {
            Potion potion = new Potion(2, 100, 150);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 20 && joueur.level < 30) {
            Potion potion = new Potion(3, 150, 250);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 30 && joueur.level < 40) {
            Potion potion = new Potion(4, 200, 350);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 40 && joueur.level < 50) {
            Potion potion = new Potion(5, 250, 400);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 50 && joueur.level < 60) {
            Potion potion = new Potion(6, 300, 550);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 60 && joueur.level < 70) {
            Potion potion = new Potion(7, 350, 650);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 70 && joueur.level < 80) {
            Potion potion = new Potion(8, 400, 750);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 80 && joueur.level < 90) {
            Potion potion = new Potion(9, 450, 850);
            potion.acheterPotions(joueur);
        }
        if (joueur.level > 90 && joueur.level < 100) {
            Potion potion = new Potion(10, 500, 1000);
            potion.acheterPotions(joueur);
        }
    }
    /**
     *
     * @param joueur
     * Méthode qui renvoie le niveau de la potion en fonction de celui du joueur.
     */
    public static void getPotion(Personnage joueur){
        if (joueur.level < 10) {    // si niveau joueur <10, permet d'acheter une potion niveau 1.
            Potion potion = new Potion(1, 50, 100);
        }
        if (joueur.level > 10 && joueur.level < 20) {
            Potion potion = new Potion(2, 100, 150);
        }
        if (joueur.level > 20 && joueur.level < 30) {
            Potion potion = new Potion(3, 150, 250);
        }
        if (joueur.level > 30 && joueur.level < 40) {
            Potion potion = new Potion(4, 200, 350);
        }
        if (joueur.level > 40 && joueur.level < 50) {
            Potion potion = new Potion(5, 250, 400);
        }
        if (joueur.level > 50 && joueur.level < 60) {
            Potion potion = new Potion(6, 300, 550);
        }
        if (joueur.level > 60 && joueur.level < 70) {
            Potion potion = new Potion(7, 350, 650);
        }
        if (joueur.level > 70 && joueur.level < 80) {
            Potion potion = new Potion(8, 400, 750);
        }
        if (joueur.level > 80 && joueur.level < 90) {
            Potion potion = new Potion(9, 450, 850);
        }
        if (joueur.level > 90 && joueur.level < 100) {
            Potion potion = new Potion(10, 500, 1000);
        }
    }

    @Override
    public void acheterPotions(Personnage joueur) {

        JFrame f = new JFrame();
        int or = joueur.getOr();
        int nbPotionsAchetes = Integer.parseInt(JOptionPane.showInputDialog(f,
                "Vous avez " + joueur.or + " pièces d'or." +
                        "\nCombien voulez-vous acheter de potions de niveau " + this.niveau + " ?"
                        + "\n\nPrix: " + this.prix
                        + "\nPV rendus: " + this.soinPV));
        if (joueur.or >= this.prix * nbPotionsAchetes) {    // Vérifie que le joueur possède assez d'or pour acheter la potion.
            joueur.setOr(joueur.or - this.prix * nbPotionsAchetes);
            joueur.setNbPotions(joueur.nbPotions + nbPotionsAchetes);
            if(nbPotionsAchetes==1){
                JOptionPane.showMessageDialog(f, "Vous avez acheté " + nbPotionsAchetes + " potion de soin.");
            }
            if(nbPotionsAchetes>1){
                JOptionPane.showMessageDialog(f, "Vous avez acheté " + nbPotionsAchetes + " potions de soin.");
            }
        } else {
            JOptionPane.showMessageDialog(f, "Vous n'avez pas assez d'argent pour acheter cet article!",
                    "Marchand", -1);
        };
    }

    @Override
    public void vendrePotions() {

    }

    @Override
    public void acheterEquipement(Personnage joueur) {

    }

    @Override
    public void vendreEquipement() {

    }
}
