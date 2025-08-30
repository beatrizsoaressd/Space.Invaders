class EnemyManager {
    constructor(game) {
        this.game = game;
        this.enemies = [];
        this.spawnInterval = 2000;
        this.lastSpawn = 0;
    }

    update(deltaTime) {
        if (Date.now() - this.lastSpawn > this.spawnInterval) {
            this.spawnEnemy();
            this.lastSpawn = Date.now();
        }
        this.enemies.forEach(enemy => enemy.update());
        this.enemies = this.enemies.filter(enemy => !enemy.markedForDeletion);
    }

    draw(context) {
        this.enemies.forEach(enemy => enemy.draw(context));
    }

    spawnEnemy(){
        const x = Math.random() * (this.game.width - 40);
        const y = -40;
        this.enemies.push(new Enemy(this.game, x, y));
    }
}