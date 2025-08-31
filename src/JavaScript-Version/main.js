//espera a página HTML carregar
window.addEventListener('load', function(){
    const menuScreen = document.getElementById('menu');
    const gameCanvas = document.getElementById('gameCanvas');
    const startButton = document.getElementById('startButton');
    const tutorialButton = document.getElementById('tutorialButton');
    const exitButton = document.getElementById('exitButton');

    const tutorialOverlay = document.getElementById('tutorialOverlay');
    const closeTutorialButton = document.getElementById('closeTutorial');

    let game = null;
    let animationFrameId = null;

    function startGame() {
       menuScreen.classList.add('hidden');
       gameCanvas.classList.remove('hidden');

       gameCanvas.width = GameConfig.WINDOW_WIDTH;
       gameCanvas.height = GameConfig.WINDOW_HEIGHT;
        game = new Game(gameCanvas, goToMenu);
        let lastTime = 0;

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
       tutorialButton.addEventListener('click', () => {
           tutorialOverlay.classList.remove('hidden');
           });
       //lógica para o botão 'X'
           closeTutorialButton.addEventListener('click', () => {
               tutorialOverlay.classList.add('hidden');
           });

       tutorialOverlay.addEventListener('click', (event) => {
               if (event.target === tutorialOverlay) {
                   tutorialOverlay.classList.add('hidden');
               }
       });
       exitButton.addEventListener('click', () => {
               /*botao de sair feito somente para fins de idêntica aparência ao código do java,
               explicaremos o motivo no README */
               alert("Ação 'SAIR' desativada no navegador.");
           });
   });