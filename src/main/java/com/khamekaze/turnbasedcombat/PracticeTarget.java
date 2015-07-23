package com.khamekaze.turnbasedcombat;

import java.util.Random;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Khamekaze
 */
public class PracticeTarget {
    
    private int hp, defence, attack;
    private Shape targetShape;
    private Random r = new Random();
    
    public PracticeTarget(int hp, int defence, int attack, int posX, int posY) {
        this.defence = defence;
        this.hp = hp;
        this.attack = attack;
        targetShape = new Rectangle(posX, posY, 150, 300);
    }
    
    public void enemyActionAttack(Player player) {
        int attackPower = r.nextInt(8);
        int actualDefence = player.getDefence();
        if(attackPower <= 3)
            attackPower = attack - attackPower;
        else if(attackPower > 3)
            attackPower = attack + attackPower;
        int damage = attackPower - actualDefence;
        if(damage < 5) {
            damage = 5;
        }
        player.setHp(player.getHp() - damage);
        System.out.println("Player attacked target for: " + attackPower + ", dealed: " + damage);
    }

    public Shape getTargetShape() {
        return targetShape;
    }

    public void setTargetShape(Shape targetShape) {
        this.targetShape = targetShape;
    }
    
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
    
    

}
