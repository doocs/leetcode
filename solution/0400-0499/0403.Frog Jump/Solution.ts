function canCross(stones: number[]): boolean {
    const n = stones.length;
    const pos: Map<number, number> = new Map();
    for (let i = 0; i < n; ++i) {
        pos.set(stones[i], i);
    }
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(-1));
    const dfs = (i: number, k: number): boolean => {
        if (i === n - 1) {
            return true;
        }
        if (f[i][k] !== -1) {
            return f[i][k] === 1;
        }
        for (let j = k - 1; j <= k + 1; ++j) {
            if (j > 0 && pos.has(stones[i] + j)) {
                if (dfs(pos.get(stones[i] + j)!, j)) {
                    f[i][k] = 1;
                    return true;
                }
            }
        }
        f[i][k] = 0;
        return false;
    };
    return dfs(0, 0);
}
