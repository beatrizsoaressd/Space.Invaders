class Game {
    constructor(canvas) {
        this.canvas = canvas;
        this.width = this.canvas.width;
        this.height = this.canvas.height;
        this.context = this.canvas.getContext('2d');

        this.input = new InputHandler();
        this.player = new Player(this);
        this.enemyManager = new EnemyManager(this);

        this.bullets = [];
        this.shootCooldown = 300;
        this.lastShotTime = 0;

    }

    update(timestamp,deltaTime) {
        this.player.update(this.input);
        this.enemyManager.update(deltaTime);

        if (this.input.keys.includes('Space') && (timestamp - this.lastShotTime > this.shootCooldown)) {
           this.bullets.push(this.player.shoot());
           this.lastShotTime = timestamp;
        }
        this.bullets.forEach(bullet => {
            bullet.update();
        });

        this.bullets = this.bullets.filter(bullet => !bullet.markedForDeletion);
        this.bullets.forEach(bullet => {
            this.enemyManager.enemies.forEach(enemy => {
                 if (bullet.x < enemy.x + enemy.width &&
                     bullet.x + bullet.width > enemy.x &&
                     bullet.y < enemy.y + enemy.height &&
                     bullet.y + bullet.height > enemy.y) {
                     bullet.markedForDeletion = true;
                     enemy.markedForDeletion = true;
                     }
                 });
            });

    }

    draw() {
        this.context.clearRect(0, 0, this.width, this.height);
        this.player.draw(this.context);
        this.enemyManager.draw(this.context);

        this.bullets.forEach(bullet => {
        bullet.draw(this.context);
        });
    }
}