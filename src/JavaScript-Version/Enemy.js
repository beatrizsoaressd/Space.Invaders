class Enemy {
    constructor(game, x, y) {
        this.game = game;
        this.width = 36;
        this.height = 36;
        this.x = x;
        this.y = y;
        this.speedX = 0;
        this.speedY = 0;

        this.image = new Image();
        this.image.src = 'assets/blue_alien_black_36x36.png';
        this.spriteCol = 0;
        this.spriteRow = 0;
        this.spriteWidth = 36;
        this.spriteHeight = 36;

        //controle da animação
        this.maxFrames = 2;
        this.frameTimer = 0;
        this.frameInterval = 400;
        this.currentFrame = 0;

        this.markedForDeletion = false;
    }

    update(deltaTime) {
        this.frameTimer += deltaTime;
        if (this.frameTimer > this.frameInterval) {
            this.currentFrame = (this.currentFrame + 1) % this.maxFrames;
            this.spriteCol = this.currentFrame;
            this.frameTimer = 0;
        }
    }

    draw(context) {
        context.drawImage(
            this.image,
            this.spriteCol * this.spriteWidth,
            this.spriteRow * this.spriteHeight,
            this.spriteWidth,
            this.spriteHeight,
            this.x,
            this.y,
            this.width,
            this.height
        );
    }
}