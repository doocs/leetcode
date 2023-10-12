function superEggDrop(k: number, n: number): number {
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    const dfs = (i: number, j: number): number => {
        if (i < 1) {
            return 0;
        }
        if (j === 1) {
            return i;
        }
        if (f[i][j]) {
            return f[i][j];
        }
        let l = 1;
        let r = i;
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            const a = dfs(mid - 1, j - 1);
            const b = dfs(i - mid, j);
            if (a <= b) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (f[i][j] = Math.max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1);
    };
    return dfs(n, k);
}
