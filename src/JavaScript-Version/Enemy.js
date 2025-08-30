class Enemy {
    constructor(game, x, y) {
        this.game = game;
        this.width = 40;
        this.height = 40;
        this.x = x;
        this.y = y;
        this.speedX = 1;
        this.speedY = 0.3;
        this.image = new Image();
        this.image.src = 'JavaScript-Version/assets/blue_alien_black_36x36.png';
        this.markedForDeletion = false;
    }

    update() {
        this.x += this.speedX;
        this.y += this.speedY;

        if (this.x < 0 || this.x > this.game.width - this.width) {
            this.speedX *= -1;
        }
        if (this.y > this.game.height) {
            this.markedForDeletion = true;
        }
    }

    draw(context) {
        context.drawImage(this.image, this.x, this.y, this.width, this.height);
    }
}