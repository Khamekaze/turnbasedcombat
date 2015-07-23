package com.khamekaze.turnbasedcombat;

import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Khamekaze
 */
public class Main extends StateBasedGame {
    
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
    public static final boolean FULLSCREEN = false;
    
    public static final int COMBATSTATE = 0;

    public Main() {
        super("TurnBasedCombat");
    }
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new CombatState());
        this.enterState(0);
    }
    
    public static void main(String[] args) {
        try {
                    //The gamecontainer used by Slick, this is where everything will be contained
                    AppGameContainer appgc = new AppGameContainer(new Main());
                    appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, FULLSCREEN);
                    appgc.getFPS();
                    appgc.setShowFPS(false);
                    appgc.setTargetFrameRate(100);
                    appgc.setAlwaysRender(true);
                    appgc.start();
            } catch (SlickException ex) {
                    Logger.getLogger(ex.toString());
            }

    }

    

}
