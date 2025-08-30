class Game {
    constructor(canvas) {
        this.canvas = canvas;
        this.width = this.canvas.width;
        this.height = this.canvas.height;
        this.context = this.canvas.getContext('2d');

        this.input = new InputHandler();
        this.player = new Player(this);

        this.bullets = [];
        this.shootCooldown = 300;
        this.lastShotTime = 0;

        this.enemyManager = new EnemyManager(this);
    }

    update(timestamp) {
        this.player.update(this.input);

        if (this.input.keys.includes('Space') && (timestamp - this.lastShotTime > this.shootCooldown)) {
            this.bullets.push(this.player.shoot());
            this.lastShotTime = timestamp; //reseta o tempo do último tiro
        }

        this.bullets.forEach(bullet => {
                    bullet.update();
        });

        this.bullets = this.bullets.filter(bullet => !bullet.markedForDeletion);

        this.enemyManager.update();
    }

    draw() {
        this.context.clearRect(0, 0, this.width, this.height);
        this.player.draw(this.context);

        this.bullets.forEach(bullet => {
        bullet.draw(this.context);
        });

        this.enemyManager.draw(this.context);
    }
}