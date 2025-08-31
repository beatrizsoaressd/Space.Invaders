class Game {
    constructor(canvas, onGoToMenu) {
        this.canvas = canvas;
        this.width = this.canvas.width;
        this.height = this.canvas.height;
        this.context = this.canvas.getContext('2d');
        this.onGoToMenu = onGoToMenu;
        this.GAMESTATE = {RUNNING: 0, VICTORY: 1, GAMEOVER: 2};
        this.gameState = this.GAMESTATE.RUNNING;

        this.input = new InputHandler();
        this.player = new Player(this);
        this.enemyManager = new EnemyManager(this);

        this.bullets = [];
        this.shootCooldown = 300;
        this.lastShotTime = 0;
        this.score = 0;
        this.currentWave = 1;
        this.canvas.addEventListener('click', this.handleEndScreenClick.bind(this));
    }

    update(timestamp,deltaTime) {
        if (this.gameState !== this.GAMESTATE.RUNNING) return;
        this.player.update(this.input);
        this.enemyManager.update(deltaTime);

        if (this.input.keys.includes('Space') && (timestamp - this.lastShotTime > this.shootCooldown)) {
           this.bullets.push(this.player.shoot());
           this.lastShotTime = timestamp;
        }
        this.bullets.forEach(bullet => bullet.update());
        this.bullets = this.bullets.filter(bullet => !bullet.markedForDeletion);

        this.handleCollisions();

        //condição de Vitória
        if (this.enemyManager.enemies.length === 0) {
            if (this.currentWave >= 5) {
                this.gameState = this.GAMESTATE.VICTORY;
            } else {
                this.currentWave++;
                this.enemyManager.nextWave(this.currentWave);
            }
        }
        //condição de Derrota
        this.enemyManager.enemies.forEach(enemy => {
            if (this.checkCollision(this.player, enemy)) {
                this.gameState = this.GAMESTATE.GAMEOVER;
            }
        });
    }

    draw() {
        this.context.clearRect(0, 0, this.width, this.height);
        this.player.draw(this.context);
        this.enemyManager.draw(this.context);
        this.bullets.forEach(bullet => bullet.draw(this.context));
        this.drawHUD();

        if (this.gameState !== this.GAMESTATE.RUNNING) {
            this.drawEndScreen();
        }

    }
    drawHUD() {
            this.context.fillStyle = 'white';
            this.context.font = '20px "Press Start 2P"';

            this.context.fillText('PONTUAÇÃO: ' + this.score, 20, 30);

            const waveText = 'WAVE: ' + this.currentWave;
            const waveTextWidth = this.context.measureText(waveText).width;
            this.context.fillText(waveText, this.width - waveTextWidth - 20, 30);
        }

        drawEndScreen() {
            //fundo semi-transparente para escurecer
            this.context.fillStyle = 'rgba(0, 0, 0, 0.7)';
            this.context.fillRect(0, 0, this.width, this.height);

            let message = '';
            let color = '';

            if (this.gameState === this.GAMESTATE.VICTORY) {
                message = 'VOCÊ VENCEU!';
                color = 'limegreen';
            } else if (this.gameState === this.GAMESTATE.GAMEOVER) {
                message = 'VOCÊ PERDEU!';
                color = 'red';
            }

            this.context.font = '48px "Press Start 2P"';
            this.context.fillStyle = color;
            this.context.textAlign = 'center';
            this.context.fillText(message, this.width / 2, this.height / 2 - 50);

            this.buttonRect = { x: this.width / 2 - 110, y: this.height / 2 + 30, width: 220, height: 40 };
            this.context.strokeStyle = 'limegreen';
            this.context.lineWidth = 2;
            this.context.strokeRect(this.buttonRect.x, this.buttonRect.y, this.buttonRect.width, this.buttonRect.height);

            this.context.font = '16px "Press Start 2P"';
            this.context.fillStyle = 'limegreen';
            this.context.fillText('MENU', this.width / 2, this.height / 2 + 58);
        }

        handleCollisions() {
            this.bullets.forEach(bullet => {
                this.enemyManager.enemies.forEach(enemy => {
                     if (!enemy.markedForDeletion && this.checkCollision(bullet, enemy)) {
                         bullet.markedForDeletion = true;
                         enemy.markedForDeletion = true;
                         this.score += GameConfig.SCORE_PER_ENEMY;
                     }
                });
            });
        }

        checkCollision(rect1, rect2) {
            return (
                rect1.x < rect2.x + rect2.width &&
                rect1.x + rect1.width > rect2.x &&
                rect1.y < rect2.y + rect2.height &&
                rect1.y + rect1.height > rect2.y
            );
        }

        handleEndScreenClick(event) {
            if (this.gameState === this.GAMESTATE.RUNNING) return;

            const rect = this.canvas.getBoundingClientRect();
            const mouseX = event.clientX - rect.left;
            const mouseY = event.clientY - rect.top;

            if (mouseX >= this.buttonRect.x && mouseX <= this.buttonRect.x + this.buttonRect.width &&
                mouseY >= this.buttonRect.y && mouseY <= this.buttonRect.y + this.buttonRect.height) {
                this.onGoToMenu();
            }
        }
}