class Bullet {
    constructor(game, x, y) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = 6;
        this.height = 20;
        this.speed = 2.0;
        this.image = new Image();
        this.image.src = 'assets/projectiles_16x16.png';

        //seleciona o sprite laser azul na imagem de projeteis
        this.spriteCol = 1;
        this.spriteRow = 0;
        this.spriteWidth = 16;
        this.spriteHeight = 16;

        this.markedForDeletion = false;
    }

    update() {
        this.y -= this.speed;
        if (this.y < 0) {
            this.markedForDeletion = true;
        }
    }

    draw(context) {
        //com 9 argumentos para recortar uma parte da spritesheet
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