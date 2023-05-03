function stoneGameIII(stoneValue: number[]): string {
    const n = stoneValue.length;
    const inf = 1 << 30;
    const f: number[] = new Array(n).fill(inf);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== inf) {
            return f[i];
        }
        let ans = -inf;
        let s = 0;
        for (let j = 0; j < 3 && i + j < n; ++j) {
            s += stoneValue[i + j];
            ans = Math.max(ans, s - dfs(i + j + 1));
        }
        return (f[i] = ans);
    };
    const ans = dfs(0);
    if (ans === 0) {
        return 'Tie';
    }
    return ans > 0 ? 'Alice' : 'Bob';
}
