package com.ck.domain;
//ָ�ӹ�
public class Commander extends SpecialTroops {
    private int commanderRating;//ָ�ӵȼ�

    public Commander() {
    }

    public Commander(int id, String name, int age, double fighting, Weapon weapon, double field, int commanderRating) {
        super(id, name, age, fighting, weapon, field);
        this.commanderRating = commanderRating;
    }

    public int getCommanderRating() {
        return this.commanderRating;
    }

    public void setCommanderRating(int commanderRating) {
        this.commanderRating = commanderRating;
    }

    public String getDetailsForTeam() {
        return this.getMemberId() + "/" + this.getDetails() + "\t" + "ָ�ӹ�" + "\t" + this.getField() + "\t\t" + this.getCommanderRating() + "��\t";
    }

    public String toString() {
        return this.getDetails() + "\tָ�ӹ�\t" + this.getStatus() + "\t" + this.getField() + "\t  " + this.commanderRating + "��\t" + this.getWeapon();
    }

}
