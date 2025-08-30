class EnemyManager {
  constructor(game) {
    this.game = game;
    this.enemies = [];

    this.cols = 8;
    this.rows = 3;
    this.hSpace = 54;
    this.vSpace = 42;
    this.startX = 20;
    this.startY = 20;
    this.edgeMargin = 4;

    this.stepPixels = 6;
    this.dropPixels = 30;
    this.baseStepTimeMs = 0.5;
    this.minStepTimeMs = 60;
    this.waveAccel = 0.18;
    this.scarcityBoostMax = 0.5;

    this.dir = 1;
    this.stepTimer = 0;
    this.wave = 1;

    this._buildFormation();
    this._resetTimers();
  }

  _buildFormation() {
    this.enemies = [];
    for (let r = 0; r < this.rows; r++) {
      for (let c = 0; c < this.cols; c++) {
        const x = this.startX + c * this.hSpace;
        const y = this.startY + r * this.vSpace;
        this.enemies.push(new Enemy(this.game, x, y));
      }
    }
    this.dir = 1;
  }

  _resetTimers() {
    const factor = Math.max(0, this.wave - 1) * this.waveAccel;
    const t = this.baseStepTimeMs * (1 - factor);
    this.stepTimer = Math.max(this.minStepTimeMs, t);
  }

  get _aliveCount() {
    let n = 0;
    for (const e of this.enemies) if (!e.markedForDeletion) n++;
    return n;
  }

  _leftmostAliveX() {
    let min = Infinity;
    for (const e of this.enemies) if (!e.markedForDeletion) min = Math.min(min, e.x);
    return min === Infinity ? 0 : min;
  }

  _rightmostAliveX() {
    let max = -Infinity;
    for (const e of this.enemies) if (!e.markedForDeletion) max = Math.max(max, e.x + e.width);
    return max === -Infinity ? 0 : max;
  }

  _stepOnce() {
    const left = this._leftmostAliveX();
    const right = this._rightmostAliveX();

    const wouldLeft  = left  + this.dir * this.stepPixels;
    const wouldRight = right + this.dir * this.stepPixels;

    const margin = this.edgeMargin;
    const width  = this.game.width;

    const hitEdge = (wouldLeft <= margin) || (wouldRight >= (width - margin));

    if (hitEdge) {
      for (const e of this.enemies) if (!e.markedForDeletion) e.y += this.dropPixels;
      this.dir *= -1;
    } else {
      for (const e of this.enemies) if (!e.markedForDeletion) e.x += this.dir * this.stepPixels;
    }
  }

  update(deltaTime) {
    for (const e of this.enemies) {
        if (!e.markedForDeletion) e.update(deltaTime);
    }

    this.stepTimer -= deltaTime;
    while (this.stepTimer <= 0) {
        this._stepOnce();
        this.stepTimer += this._currentStepInterval();
    }

    this.enemies = this.enemies.filter(e => !e.markedForDeletion);

    if (this._aliveCount === 0) {
        this.nextWave();
    }
  }

  draw(context) {
    for (const e of this.enemies) e.draw(context);
  }

  nextWave() {
    this.wave += 1;
    this._buildFormation();
    this._resetTimers();
  }

  _currentStepInterval() {
    const base = Math.max(
      this.minStepTimeMs,
      this.baseStepTimeMs * (1 - Math.max(0, this.wave - 1) * this.waveAccel)
    );

    const total = this.cols * this.rows;
    const alive = Math.max(1, this._aliveCount);
    const scarcity = 1 - (alive / total);
    const scarcityBoost = this.scarcityBoostMax * scarcity;

    return Math.max(this.minStepTimeMs, base * (1 - scarcityBoost));
  }
}
