import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;

public class Equipement {
    protected String nom;
    protected int prix;

    public Equipement(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }
}