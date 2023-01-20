function minSideJumps(obstacles: number[]): number {
    const inf = 1 << 30;
    const f = [1, 0, 1];
    for (let i = 1; i < obstacles.length; ++i) {
        for (let j = 0; j < 3; ++j) {
            if (obstacles[i] == j + 1) {
                f[j] = inf;
                break;
            }
        }
        const x = Math.min(...f) + 1;
        for (let j = 0; j < 3; ++j) {
            if (obstacles[i] != j + 1) {
                f[j] = Math.min(f[j], x);
            }
        }
    }
    return Math.min(...f);
}
