class Enemy {
    constructor(game, x, y) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = 36;
        this.height = 36;

        this.image = new Image();
        this.image.src = 'assets/blue_alien_black_36x36.png';
        //controle da animação
        this.frameX = 0;
        this.maxFrames = 5;
        this.fps = 6;
        this.frameTimer = 0;
        this.frameInterval = 1000 / this.fps;

        this.markedForDeletion = false;
    }

    update(deltaTime) {
        if (this.frameTimer > this.frameInterval) {
            if (this.frameX < this.maxFrame) this.frameX++;
            else this.frameX = 0;
            this.frameTimer = 0;
        } else {
            this.frameTimer += deltaTime;
        }
    }

    draw(context) {
        context.drawImage(
            this.image,
            this.frameX * 36,
            0,
            36,
            36,
            this.x,
            this.y,
            this.width,
            this.height
        );
    }
}