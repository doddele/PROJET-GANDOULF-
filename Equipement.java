import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;
import java.lang.Math;

public class Equipement implements Marchand{
    protected String nom;
    protected int prix;
    protected String typeEquipement;
    protected int attaqueArme;
    protected int defenseArme;

    public Equipement(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }
    public Equipement(){
        this.nom = "";
        this.prix = 0;
    }


    @Override
    public void acheterPotions(Personnage joueur) {

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
    public void acheterArmure(Personnage joueur){};
    public void acheterArme(Personnage joueur){};
}