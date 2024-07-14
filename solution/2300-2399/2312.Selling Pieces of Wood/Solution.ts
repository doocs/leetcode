function sellingWood(m: number, n: number, prices: number[][]): number {
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(-1));
    const d: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (const [h, w, p] of prices) {
        d[h][w] = p;
    }

    const dfs = (h: number, w: number): number => {
        if (f[h][w] !== -1) {
            return f[h][w];
        }

        let ans = d[h][w];
        for (let i = 1; i <= Math.floor(h / 2); i++) {
            ans = Math.max(ans, dfs(i, w) + dfs(h - i, w));
        }
        for (let i = 1; i <= Math.floor(w / 2); i++) {
            ans = Math.max(ans, dfs(h, i) + dfs(h, w - i));
        }
        return (f[h][w] = ans);
    };

    return dfs(m, n);
}
