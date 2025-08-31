//espera a página HTML carregar
window.addEventListener('load', function(){
    const menuScreen = document.getElementById('menu');
    const gameCanvas = document.getElementById('gameCanvas');
    const startButton = document.getElementById('startButton');

    let game = null;
    let animationFrameId = null;

    function startGame() {
       menuScreen.classList.add('hidden');
       gameCanvas.classList.remove('hidden');

       gameCanvas.width = GameConfig.WINDOW_WIDTH;
       gameCanvas.height = GameConfig.WINDOW_HEIGHT;
        game = new Game(gameCanvas, goToMenu);
        let lastTime = 0;

        //Game Loop
        function animate(timestamp) {
            const deltaTime = timestamp - lastTime;
            lastTime = timestamp;
            game.update(timestamp, deltaTime);
            game.draw();

            animationFrameId = requestAnimationFrame(animate);
        }
        animate(0);
   }
   function goToMenu() {
           if (animationFrameId) {
               cancelAnimationFrame(animationFrameId);
           }
           gameCanvas.classList.add('hidden');
           menuScreen.classList.remove('hidden');
       }

       startButton.addEventListener('click', startGame);
   });