package com.khamekaze.turnbasedcombat;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Khamekaze
 */
public class CombatState extends BasicGameState {
    
    private Combat arena;
    private boolean actionMenuOpen = false;

    @Override
    public int getID() {
        return Main.COMBATSTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        arena = new Combat();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        arena.render(g, actionMenuOpen);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        Input input = gc.getInput();
        
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        
        arena.update(mouseX, mouseY);
        if(arena.isEnemyTurnOver()) {
            if(mouseX >= arena.getPlayerX() && mouseX <= arena.getPlayerX() + 50 &&
                    mouseY >= arena.getPlayerY() && mouseY <= arena.getPlayerY() + 100) {
                if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    if(!actionMenuOpen) {
                        arena.setActionAttack(false);
                        arena.setActionItem(false);
                        arena.setActionMagic(false);
                        actionMenuOpen = true;
                    } else {
                        actionMenuOpen = false;
                    }
                }
            }

            if(actionMenuOpen) {
                for(Shape s : arena.getActionButtons()) {
                    if(arena.getMouseHitBox().intersects(s)) {
                        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                            if(s == arena.getAttackButton()) {
                                arena.setActionAttack(true);
                                arena.setActionItem(false);
                                arena.setActionMagic(false);
                                actionMenuOpen = false;
                            } else if(s == arena.getItemButton()) {
                                arena.setActionAttack(false);
                                arena.setActionItem(true);
                                arena.setActionMagic(false);
                                actionMenuOpen = false;
                            } else if(s == arena.getMagicButton()) {
                                arena.setActionAttack(false);
                                arena.setActionItem(false);
                                arena.setActionMagic(true);
                                actionMenuOpen = false;
                            }
                        }
                    }
                }
            }

            if(arena.isActionAttack()) {
                if(arena.getMouseHitBox().intersects(arena.getTarget().getTargetShape())) {
                    if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        arena.setPlayerHasSelectedTarget(true);
                        arena.setActionAttack(false);
                        arena.getPlayer().playerActionAttack(arena.getTarget());
                        arena.setPlayerTurnOver(true);
                        arena.setEnemyTurnOver(false);
                    }
                }
            }
        } else if(arena.isPlayerTurnOver()) {
            arena.setEnemyHasSelectedTarget(true);
            if(arena.getWaitTime() == 200) {
                arena.getTarget().enemyActionAttack(arena.getPlayer());
                arena.setEnemyTurnOver(true);
                arena.setPlayerTurnOver(false);
            }
        }
    }
}
