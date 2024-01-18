function minimumOperationsToMakeEqual(x: number, y: number): number {
    const f: Map<number, number> = new Map();
    const dfs = (x: number): number => {
        if (y >= x) {
            return y - x;
        }
        if (f.has(x)) {
            return f.get(x)!;
        }
        const a = (x % 5) + 1 + dfs((x / 5) | 0);
        const b = 5 - (x % 5) + 1 + dfs(((x / 5) | 0) + 1);
        const c = (x % 11) + 1 + dfs((x / 11) | 0);
        const d = 11 - (x % 11) + 1 + dfs(((x / 11) | 0) + 1);
        const ans = Math.min(x - y, a, b, c, d);
        f.set(x, ans);
        return ans;
    };
    return dfs(x);
}
