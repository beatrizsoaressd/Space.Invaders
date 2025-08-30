class InputHandler {
    constructor() {
        this.keys = []; //guarda as teclas pressionadas

        window.addEventListener('keydown', (e) => {
            if ([' ', 'Space', 'Spacebar', 'ArrowLeft', 'ArrowRight', 'ArrowUp', 'ArrowDown'].includes(e.code) ||
                [' ', 'ArrowLeft', 'ArrowRight', 'ArrowUp', 'ArrowDown'].includes(e.key)) {
                    e.preventDefault();
                }
            if (!this.keys.includes(e.code)) this.keys.push(e.code);
            if (!this.keys.includes(e.key))  this.keys.push(e.key);
        });

        window.addEventListener('keyup', (e) => {
            this.keys = this.keys.filter(k => k !== e.code && k !== e.key);
        });
    }
}