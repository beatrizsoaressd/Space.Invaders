class Game {
    constructor(canvas) {
        this.canvas = canvas;
        this.width = this.canvas.width;
        this.height = this.canvas.height;
        this.context = this.canvas.getContext('2d');

        //objetos do jogo
        this.input = new InputHandler();
        this.player = new Player(this);
    }

    update() {
        this.player.update(this.input);
    }

    draw() {
        this.context.clearRect(0, 0, this.width, this.height);
        this.player.draw(this.context);
    }
}