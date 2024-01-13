function stringCount(n: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[][][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: 2 }, () =>
            Array.from({ length: 3 }, () => Array.from({ length: 2 }, () => -1)),
        ),
    );
    const dfs = (i: number, l: number, e: number, t: number): number => {
        if (i === 0) {
            return l === 1 && e === 2 && t === 1 ? 1 : 0;
        }
        if (f[i][l][e][t] !== -1) {
            return f[i][l][e][t];
        }
        const a = (dfs(i - 1, l, e, t) * 23) % mod;
        const b = dfs(i - 1, Math.min(1, l + 1), e, t);
        const c = dfs(i - 1, l, Math.min(2, e + 1), t);
        const d = dfs(i - 1, l, e, Math.min(1, t + 1));
        return (f[i][l][e][t] = (a + b + c + d) % mod);
    };
    return dfs(n, 0, 0, 0);
}
