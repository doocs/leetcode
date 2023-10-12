function maximumCost(n: number, highways: number[][], k: number): number {
    if (k >= n) {
        return -1;
    }
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b, cost] of highways) {
        g[a].push([b, cost]);
        g[b].push([a, cost]);
    }
    const f: number[][] = Array(1 << n)
        .fill(0)
        .map(() => Array(n).fill(-(1 << 30)));
    for (let i = 0; i < n; ++i) {
        f[1 << i][i] = 0;
    }
    let ans = -1;
    for (let i = 0; i < 1 << n; ++i) {
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                for (const [h, cost] of g[j]) {
                    if ((i >> h) & 1) {
                        f[i][j] = Math.max(f[i][j], f[i ^ (1 << j)][h] + cost);
                    }
                }
            }
            if (bitCount(i) === k + 1) {
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
