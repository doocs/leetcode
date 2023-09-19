function getMinDistSum(positions: number[][]): number {
    const n = positions.length;
    let [x, y] = [0, 0];
    for (const [px, py] of positions) {
        x += px;
        y += py;
    }
    x /= n;
    y /= n;
    const decay = 0.999;
    const eps = 1e-6;
    let alpha = 0.5;
    while (true) {
        let [gradX, gradY] = [0, 0];
        let dist = 0;
        for (const [px, py] of positions) {
            const a = x - px;
            const b = y - py;
            const c = Math.sqrt(a * a + b * b);
            gradX += a / (c + 1e-8);
            gradY += b / (c + 1e-8);
            dist += c;
        }
        const dx = gradX * alpha;
        const dy = gradY * alpha;
        if (Math.abs(dx) <= eps && Math.abs(dy) <= eps) {
            return dist;
        }
        x -= dx;
        y -= dy;
        alpha *= decay;
    }
}
