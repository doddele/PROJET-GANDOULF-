import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Personnage {
    protected String nom;
    protected String classe;
    protected int level;
    protected int XP;
    protected int defense;
    protected int HPmax;
    protected int HP;
    protected int seuilXP;
    protected int esquive;
    protected int vitesse;
    protected String arme;
    protected String armure;
    protected int or;
    protected int nbPotions;

    public Personnage(String nom) {
        this.nom = nom;
        this.level = 0;
        this.XP = 0;
        this.defense = 0;
        this.HP = 100;
        this.HPmax = 100;
        this.seuilXP = 100;
        this.esquive = 1;
        this.vitesse = 10;
        this.arme = "vide";
        this.armure = "vide";
        this.or = 50;
        this.nbPotions = 0;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        System.out.println(this.nom);
        return this.nom;
    }
    public void setOr(int or){
        this.or = or;
    }
    public int getOr() {
        return this.or;
    }
    public void setNbPotions(int x) {
        this.nbPotions = x;
    }
    public void rename(String newNom) {
        this.nom = newNom;
    }

    public void creerBarbare(){

    }

    public void save() {}
    public void death() {
        if (this.HP <= 0) {
            JFrame g;
            g = new JFrame();
            //System.out.println(this.nom + " est mort!");
            JOptionPane.showMessageDialog(g, this.nom + " est mort.", this.nom + " : " + this.HP + "PV", -1);
        }
    }
}