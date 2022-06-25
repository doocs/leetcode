function minCost(costs: number[][]): number {
    let [r, g, b] = [0, 0, 0];
    for (const [_r, _g, _b] of costs) {
        [r, g, b] = [
            _r + Math.min(g, b),
            _g + Math.min(r, b),
            _b + Math.min(r, g),
        ];
    }
    return Math.min(r, g, b);
}
