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
}
