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

    public Equipement(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
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
}