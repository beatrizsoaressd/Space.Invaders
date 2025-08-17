package spaceinvaders;

public final class GameConfig {
    private GameConfig() {}

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final double PLAYER_SPEED = 260; // px/s
    public static final double PLAYER_COOLDOWN = 0.28; // s
    public static final double BULLET_SPEED = -480; // px/s (jogador)
    public static final double ENEMY_BULLET_SPEED = 220; // px/s

    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 20;

    public static final int ENEMY_WIDTH = 32;
    public static final int ENEMY_HEIGHT = 20;

    public static final int GRID_COLUMNS = 9;
    public static final int GRID_ROWS = 4;
    public static final int GRID_H_SPACING = 18;
    public static final int GRID_V_SPACING = 18;
    public static final int GRID_MARGIN_TOP = 60;

    public static final double ENEMY_SPEED_X = 60; // px/s (módulo)
    public static final int ENEMY_STEP_DOWN = 18; // px ao bater na borda

    public static final int INITIAL_LIVES = 3;
}