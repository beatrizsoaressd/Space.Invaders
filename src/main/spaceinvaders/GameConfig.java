package spaceinvaders;

public class GameConfig {

    //Dimensões da tela
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final String GAME_TITLE = "Space Invaders";

    //Configs do jogador
    public static final double PLAYER_SPEED = 15; // px/s
    public static final double PLAYER_COOLDOWN = 0.28; // s
    public static final int INITIAL_LIVES = 3;
    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 20;

    //Configs dos projéteis
    public static final double PROJECTILE_SPEED = -480; // px/s (jogador)
    public static final double ENEMY_PROJECTILE_SPEED = 220; // px/s
    public static final int PROJECTILE_WIDTH = 5;
    public static final int PROJECTILE_HEIGHT = 15;

    //Configs dos inimigos
    public static final int ENEMY_WIDTH = 32;
    public static final int ENEMY_HEIGHT = 20;
    public static final double ENEMY_SPEED = 60; // px/s (módulo)
    public static final int ENEMY_STEP_DOWN = 18; // px ao bater na borda
    
    //Outras configs
    public static final int GRID_COLUMNS = 3;
    public static final int GRID_ROWS = 2;
    public static final int GRID_H_SPACING = 18;
    public static final int GRID_V_SPACING = 18;
    public static final int GRID_MARGIN_TOP = 60;
    public static final int FRAME_DELAY = 16; // ~60 FPS
    public static final int SCORE_PER_ENEMY = 30;
    public static final int INITIAL_SCORE = 0;

}