function maxWeight(weights: number[], w1: number, w2: number): number {
    const f: number[][] = Array.from({ length: w1 + 1 }, () => Array(w2 + 1).fill(0));
    for (const x of weights) {
        for (let j = w1; j >= 0; j--) {
            for (let k = w2; k >= 0; k--) {
                if (x <= j) {
                    f[j][k] = Math.max(f[j][k], f[j - x][k] + x);
                }
                if (x <= k) {
                    f[j][k] = Math.max(f[j][k], f[j][k - x] + x);
                }
            }
        }
    }
    return f[w1][w2];
}
