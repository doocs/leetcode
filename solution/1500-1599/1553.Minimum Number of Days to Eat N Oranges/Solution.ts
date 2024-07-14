function minDays(n: number): number {
    const f: Record<number, number> = {};
    const dfs = (n: number): number => {
        if (n < 2) {
            return n;
        }
        if (f[n] !== undefined) {
            return f[n];
        }
        f[n] = 1 + Math.min((n % 2) + dfs((n / 2) | 0), (n % 3) + dfs((n / 3) | 0));
        return f[n];
    };
    return dfs(n);
}
