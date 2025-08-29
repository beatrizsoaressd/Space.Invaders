class Player {
    constructor(game) {
        this.game = game;
        this.width = 50;
        this.height = 50;

        //posição inicial
        this.x = (this.game.width / 2) - (this.width / 2);
        this.y = this.game.height - this.height - 10;

        this.image = new Image();
        this.image.src = 'assets/nave_azul.png';
        this.speed = GameConfig.PLAYER_SPEED;
    }

    //desenho do canvas
    draw(context) {
        context.drawImage(this.image, this.x, this.y, this.width, this.height);
    }

    update(input) {
        //movimentação
        if (input.keys.includes('ArrowLeft') || input.keys.includes('KeyA')) {
            this.x -= this.speed;
        } else if (input.keys.includes('ArrowRight') || input.keys.includes('KeyD')) {
            this.x += this.speed;
        }

        //para não sair da tela
        if (this.x < 0) this.x = 0;
        if (this.x > this.game.width - this.width) this.x = this.game.width - this.width;
    }
}