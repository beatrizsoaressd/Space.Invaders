class EnemyManager {
  constructor(game) {
    this.game = game;
    this.enemies = [];
    this.speedX = 0.6;
    this.speedY = 0;
    this.createEnemies(3, 8);
    }

    createEnemies(rows, cols) {
        for (let row = 0; row < rows; row++) {
            for (let col = 0; col < cols; col++) {
                let x = 80 + col * 50;
                let y = 80 + row * 40;
                this.enemies.push(new Enemy(this.game, x, y));
            }
        }
    }

    update(deltaTime) {
        let reverse = false;
        for (const enemy of this.enemies) {
            enemy.x += this.speedX;
            if (enemy.x <= 0 || enemy.x >= this.game.width - enemy.width) {
                reverse = true;
            }
        }

        if (reverse) {
           this.speedX *= -1;
           for (const enemy of this.enemies) {
               enemy.y += 20;
                }
           }
        this.enemies.forEach(enemy => enemy.update(deltaTime));
        this.enemies = this.enemies.filter(e => !e.markedForDeletion);
    }

  draw(context) {
    this.enemies.forEach(enemy => enemy.draw(context));
  }
  nextWave(waveNumber) {
      const rows = 3 + (waveNumber - 1);
      this.createEnemies(rows, 8);
  }
}
