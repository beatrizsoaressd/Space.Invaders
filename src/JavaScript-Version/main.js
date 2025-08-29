//espera a página HTML carregar
window.addEventListener('load', function(){
    const canvas = document.getElementById('gameCanvas');
    canvas.width = GameConfig.WINDOW_WIDTH;
    canvas.height = GameConfig.WINDOW_HEIGHT;

    const game = new Game(canvas);

    //o Game Loop
    function animate() {
        game.update();
        game.draw();

        requestAnimationFrame(animate);
    }

    animate();
});