import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Monstre {
    public String nomMonstre;
    public int PVMonstre;
    public int attaque;
    public int defense;
    public int niveau;
    public int XP;
    public int esquive;
    public int vitesse;
    public int or;

    public Monstre(String nomMonstre, int PVMonstre, int attaque, int defense, int niveau, int XP, int esquive, int vitesse, int or) {
        this.nomMonstre = nomMonstre;
        this.PVMonstre = PVMonstre;
        this.attaque = attaque;
        this.defense = defense;
        this.niveau = niveau;
        this.XP = XP;
        this.esquive = esquive;
        this.vitesse = vitesse;
        this.or = or;
    }

    public Monstre() {
    }
    public void attaquer(Personnage joueur, Armure armure){
        double nb;
        nb = Math.random();
        nb = nb * 100;
        if (nb < joueur.esquive) {
            JOptionPane.showMessageDialog(null, "Le " +
                    this.nomMonstre + " sauvage tente d'attaquer, mais " + joueur.nom +
                    " a esquivé le coup !");
        } else {
            int atkMonstre = this.attaque - joueur.defense - armure.defenseArme;

            if (atkMonstre <= 0) {  // Empêche d'avoir des dégâts négatifs.
                atkMonstre = 0;
            }
            joueur.HP -= atkMonstre;
            JOptionPane.showMessageDialog(null,
                    "Le " + this.nomMonstre + " sauvage attaque, il inflige " +
                            atkMonstre + " points de dégats");
        }
        if (joueur.HP <= 0) {
            JOptionPane.showMessageDialog(null, "Vous êtes mort.");
            //break loop;
        }
    }
}
