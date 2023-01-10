import javax.swing.*;
import java.lang.Math;
public class Arme extends Equipement implements Marchand{
    private String typeEquipement;
    private int attaqueArme;
    public Arme (String typeEquipement, String nom, int attaqueArme, int prix) {
        super(nom, prix);
        this.typeEquipement = typeEquipement;
        this.attaqueArme = attaqueArme;
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