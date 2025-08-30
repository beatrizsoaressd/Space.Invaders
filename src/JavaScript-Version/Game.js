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

    update(deltaTime) {
        this.player.update(this.input);

        if (this.shootTimer == null) this.shootTimer = 0;
        this.shootTimer -= deltaTime;

        const pressedSpace = this.input.keys.includes('Space') || this.input.keys.includes(' ');
        if (pressedSpace && this.shootTimer <= 0) {
            const shot = this.player.shoot();
            if (shot) this.bullets.push(shot);
            this.shootTimer = this.shootCooldown;
        }

        this.bullets.forEach(bullet => bullet.update(deltaTime));
        this.bullets = this.bullets.filter(bullet => !bullet.markedForDeletion);

        this.enemyManager.update(deltaTime);
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