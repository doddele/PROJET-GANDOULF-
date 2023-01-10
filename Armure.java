import java.lang.Math;
public class Armure extends Equipement {
    private String typeEquipement;
    private int defenseArme;
    public Armure (String typeEquipement,String nom, int defenseArme, int prix) {
        super(nom, prix);
        this.typeEquipement = typeEquipement;
        this.defenseArme = defenseArme;
    }
}
