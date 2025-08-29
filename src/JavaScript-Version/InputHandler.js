class InputHandler {
    constructor() {
        this.keys = []; //guarda as teclas pressionadas

        window.addEventListener('keydown', (e) => {
            if ((e.code === 'ArrowLeft' ||
                 e.code === 'ArrowRight' ||
                 e.code === 'KeyA' ||
                 e.code === 'KeyD' ||
                 e.code === 'Space')
                 && !this.keys.includes(e.code)) {
                this.keys.push(e.code);
            }
        });

        window.addEventListener('keyup', (e) => {
            const index = this.keys.indexOf(e.code);
            if (index > -1) {
                this.keys.splice(index, 1);
            }
        });
    }
}