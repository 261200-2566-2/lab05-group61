class Warrior {
    private final double Level;
    private double baseHp;
    private double baseDef;
    private Sword equippedSword;
    private HeavyArmor equippedHeavyArmor;

    public Warrior(double Level, double baseHp, double baseDef) {
        this.Level = Level;
        this.baseHp = baseHp;
        this.baseDef = baseDef;
        this.equippedSword = null;
        this.equippedHeavyArmor = null;
        updateStats();
    }

    public void equipSword(Sword sword) {
        this.equippedSword = sword;
        updateStats();
    }

    public void equipHeavyArmor(HeavyArmor HeavyArmor) {
        this.equippedHeavyArmor = HeavyArmor;
        updateStats();
    }

    public void PVP(Wizard Wizard) {
        printStats();
        Wizard.printWizardStats();
        System.out.println();

        double damageDealt = CalculateAtk();
        Wizard.DmgTaken(damageDealt);
        System.out.println("JohnSmith dealt " + damageDealt + " damage to the Wizard.");

        double damageTaken = Wizard.CalculateAtk()-baseDef;
        DmgTaken(damageTaken);
        System.out.println("JohnSmith took " + damageTaken + " damage from the Wizard.");
        System.out.println();
    }

    private double CalculateAtk() {
        double Atk = 20;
        if (equippedSword != null) {
            Atk += equippedSword.getBaseAtk() * (1 + 0.1 * Level);
        }

        return Atk;
    }

    private void updateStats() {
        if (equippedHeavyArmor != null) {
            this.baseDef += equippedHeavyArmor.getBaseDef();
        }
    }

    public void DmgTaken(double damage) {
        double actualDmg = Math.max(0, damage - getMaxDef());
        this.baseHp -= actualDmg;
    }

    public void printStats() {
        System.out.println("JohnSmith Lv: " + Level + " | Atk: " + CalculateAtk() +
                " | HP: " + getMaxHp() + " | Def: " + getMaxDef() + " | Mana: " +
                "| BaseSpeed: " );
    }


    private double getMaxHp() {
        return baseHp + (20 * Level);
    }
    public double getBaseHp() {
        return baseHp;
    }

    private double getMaxDef() {
        return baseDef;
    }

    public static void main(String[] args) {
        Sword sword = new Sword(990, 1);
        HeavyArmor HeavyArmor = new HeavyArmor(100, 1);

        Wizard Wizard = new Wizard(100, 5000);
        Warrior johnSmith = new Warrior(10, 1000, 0);

        johnSmith.PVP(Wizard);
        System.out.println();
        System.out.println("BUFF WITH OP MUSIC AND GET LEGENDARY EQUIPMENT!!!!!");
        System.out.println();
        johnSmith.equipSword(sword);
        johnSmith.equipHeavyArmor(HeavyArmor);

        while (johnSmith.getBaseHp() > 0 && Wizard.getWizardHp() > 0) {
            johnSmith.PVP(Wizard);

            if (johnSmith.getBaseHp() <= 0) {
                System.out.println();
                System.out.println("JohnSmith Hp: 0");
                System.out.println("JohnSmith has been defeated!");
                break;
            }

            if (Wizard.getWizardHp() <= 0) {
                System.out.println();
                System.out.println("Wizard Hp: 0.0 "+"JohnSmith Hp:" +johnSmith.getBaseHp());
                System.out.println("JohnSmith is victory!");
                break;
            }
        }


    }
}

record Sword(double baseAtk, double runSpeedDecrease) {
    public double getBaseAtk() {
        return baseAtk;
    }
}

record HeavyArmor(double baseDef, double runSpeedDecrease) {
    public double getBaseDef() {
        return baseDef;
    }
}

class Wizard {
    private final double WizardLv;
    private double WizardHp;

    public Wizard(double WizardLv, double WizardHp) {
        this.WizardLv = WizardLv;
        this.WizardHp = WizardHp;
    }

    public double CalculateAtk() {
        double baseAttack = 0;
        return baseAttack + (WizardLv * 3);
    }

    public void DmgTaken(double damage) {
        double actualDmg = Math.max(0, damage);
        this.WizardHp -= actualDmg;
        if(WizardHp<0){
            this.WizardHp=0;
        }
    }

    public void printWizardStats() {
        System.out.println("Wizard   Lv: " + WizardLv + " | Atk: " + CalculateAtk() + " | HP: " + WizardHp + " |");
    }


    public double getWizardHp() {
        return WizardHp;
    }
}