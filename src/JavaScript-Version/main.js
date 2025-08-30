//espera a página HTML carregar
window.addEventListener('load', function(){
    const canvas = document.getElementById('gameCanvas');
    canvas.width = GameConfig.WINDOW_WIDTH;
    canvas.height = GameConfig.WINDOW_HEIGHT;

    const game = new Game(canvas);
    let lastTime = 0;

    //o Game Loop
    function animate(timestamp) {
        const deltaTime = timestamp - lastTime;
        lastTime = timestamp;
        game.update(timestamp, deltaTime);
        game.draw();

        requestAnimationFrame(animate);
    }

    animate(0);
});