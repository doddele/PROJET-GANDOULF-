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
    String nom = "Potion";
    int niveau;
    int prix;
    int soinPV;
    public Potion(int niveau, int prix, int soinPV){
        this.niveau = niveau;
        this.prix = prix;
        this.soinPV = soinPV;
    }

    @Override
    public void acheterPotions() {
        JFrame f = new JFrame();
        Personnage joueur;
        int or = joueur.getOr();
        int nbPotionsAchetes = Integer.parseInt(JOptionPane.showInputDialog(f,
                "Vous avez " + joueur.or + " pièces d'or." +
                        "\nCombien voulez-vous acheter de potions de niveau " + this.niveau + " ?"
                        + "\n\nPrix: " + this.prix
                        + "\nPV rendus: " + this.soinPV));
        if (joueur.or >= this.prix * nbPotionsAchetes) {
            joueur.setOr(joueur.or - this.prix * nbPotionsAchetes);
            joueur.setNbPotions(joueur.nbPotions + nbPotionsAchetes);
        } else {
            JOptionPane.showMessageDialog(f, "Vous n'avez pas assez d'argent pour acheter cet article!",
                    "Marchand", -1);
        };
    }

    @Override
    public void vendrePotions() {

    }

    @Override
    public void acheterEquipement() {

    }

    @Override
    public void vendreEquipement() {

    }
}
