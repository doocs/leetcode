function leastOpsExpressTarget(x: number, target: number): number {
    const f: Map<number, number> = new Map();
    const dfs = (v: number): number => {
        if (x > v) {
            return Math.min(v * 2 - 1, 2 * (x - v));
        }
        if (f.has(v)) {
            return f.get(v)!;
        }
        let k = 2;
        let y = x * x;
        while (y < v) {
            y *= x;
            ++k;
        }
        let ans = k - 1 + dfs(v - Math.floor(y / x));
        if (y - v < v) {
            ans = Math.min(ans, k + dfs(y - v));
        }
        f.set(v, ans);
        return ans;
    };
    return dfs(target);
}
