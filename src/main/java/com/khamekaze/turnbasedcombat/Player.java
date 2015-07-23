package com.khamekaze.turnbasedcombat;

import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Khamekaze
 */
public class Player {
    private int hp, attack, defence;
    private Shape playerShape;
    private Random r = new Random();
    
    public Player(int hp, int attack, int defence, int posX, int posY) {
        this.attack = attack;
        this.defence = defence;
        this.hp = hp;
        playerShape = new Rectangle(posX, posY, 50, 100);
    }
    
    public void playerActionAttack(PracticeTarget target) {
        int attackPower = r.nextInt(8);
        int actualDefence = target.getDefence();
        if(attackPower <= 3)
            attackPower = attack - attackPower;
        else if(attackPower > 3)
            attackPower = attack + attackPower;
        int damage = attackPower - actualDefence;
        if(damage < 5) {
            damage = 5;
        }
        target.setHp(target.getHp() - damage);
        System.out.println("Player attacked target for: " + attackPower + ", dealed: " + damage);
    }

    public Shape getPlayerShape() {
        return playerShape;
    }

    public void setPlayerShape(Shape playerShape) {
        this.playerShape = playerShape;
    }
    
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
    
    
    
}
