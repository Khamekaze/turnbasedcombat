package com.khamekaze.turnbasedcombat;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Khamekaze
 */
public class Combat {
    
    private Player player;
    private PracticeTarget target;
    private int playerX = 800, playerY = 400;
    
    private Shape attackButton, itemButton, magicButton;
    private List<Shape> actionButtons;
    private Shape mouseHitBox;
    
    private boolean playerActionAttack = false, playerActionItem = false, playerActionMagic = false;
    
    public Combat() {
        player = new Player(250, 10, 5, 800, 400);
        target = new PracticeTarget(250, 5, 10, 50, 200);
        attackButton = new Circle(playerX + 25, playerY - 75, 25);
        itemButton = new Circle(playerX - 75, playerY + 50, 25);
        magicButton = new Circle(playerX + 125, playerY + 50, 25);
        actionButtons = new ArrayList<>();
        actionButtons.add(attackButton);
        actionButtons.add(itemButton);
        actionButtons.add(magicButton);
        mouseHitBox = new Circle(0, 0, 1);
        
    }
    
    public void render(Graphics g, boolean activate) {
        g.setColor(Color.white);
        g.drawString("Target HP: " + target.getHp(), 50, 100);
        g.drawString("Player HP: " + player.getHp(), playerX, playerY + 200);
        g.fill(player.getPlayerShape());
        g.fill(target.getTargetShape());
        activateActionMenu(activate, g);
        if(playerActionAttack) {
            renderSelectedActionIndicator(g, "A");
        } else if(playerActionItem) {
            renderSelectedActionIndicator(g, "I");
        } else if(playerActionMagic) {
            renderSelectedActionIndicator(g, "M");
        }
        
    }
    
    public void update(int x, int y) {
        mouseHitBox.setCenterX(x);
        mouseHitBox.setCenterY(y);
    }
    
    public void activateActionMenu(boolean activate, Graphics g) {
        if(activate) {
            for(Shape s : actionButtons) {
                g.fill(s);
            }
            g.setColor(Color.black);
            g.drawString("A", attackButton.getCenterX(), attackButton.getCenterY());
            g.drawString("M", magicButton.getCenterX(), magicButton.getCenterY());
            g.drawString("I", itemButton.getCenterX(), itemButton.getCenterY());
        }
    }
    
    public void renderSelectedActionIndicator(Graphics g, String action) {
        g.setColor(Color.white);
        Shape s = new Circle(Main.WINDOW_WIDTH / 2, 150, 25);
        g.fill(s);
        g.setColor(Color.black);
        g.drawString(action, Main.WINDOW_WIDTH / 2, 150);
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public PracticeTarget getTarget() {
        return target;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public Shape getAttackButton() {
        return attackButton;
    }

    public void setAttackButton(Shape attackButton) {
        this.attackButton = attackButton;
    }

    public Shape getItemButton() {
        return itemButton;
    }

    public void setItemButton(Shape itemButton) {
        this.itemButton = itemButton;
    }

    public Shape getMagicButton() {
        return magicButton;
    }

    public void setMagicButton(Shape magicButton) {
        this.magicButton = magicButton;
    }

    public List<Shape> getActionButtons() {
        return actionButtons;
    }

    public void setActionButtons(List<Shape> actionButtons) {
        this.actionButtons = actionButtons;
    }

    public Shape getMouseHitBox() {
        return mouseHitBox;
    }

    public void setMouseHitBox(Shape mouseHitBox) {
        this.mouseHitBox = mouseHitBox;
    }

    public boolean isActionAttack() {
        return playerActionAttack;
    }

    public void setActionAttack(boolean actionAttack) {
        this.playerActionAttack = actionAttack;
    }

    public boolean isActionItem() {
        return playerActionItem;
    }

    public void setActionItem(boolean actionItem) {
        this.playerActionItem = actionItem;
    }

    public boolean isActionMagic() {
        return playerActionMagic;
    }

    public void setActionMagic(boolean actionMagic) {
        this.playerActionMagic = actionMagic;
    }
    
    
    
}
