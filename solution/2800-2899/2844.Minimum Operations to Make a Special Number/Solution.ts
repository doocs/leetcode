function minimumOperations(num: string): number {
    const n = num.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: 25 }, () => -1));
    const dfs = (i: number, k: number): number => {
        if (i === n) {
            return k === 0 ? 0 : n;
        }
        if (f[i][k] !== -1) {
            return f[i][k];
        }
        f[i][k] = dfs(i + 1, k) + 1;
        f[i][k] = Math.min(f[i][k], dfs(i + 1, (k * 10 + Number(num[i])) % 25));
        return f[i][k];
    };
    return dfs(0, 0);
}
