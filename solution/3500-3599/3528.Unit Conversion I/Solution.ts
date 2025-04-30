function baseUnitConversions(conversions: number[][]): number[] {
    const mod = BigInt(1e9 + 7);
    const n = conversions.length + 1;
    const g: { t: number; w: number }[][] = Array.from({ length: n }, () => []);
    for (const [s, t, w] of conversions) {
        g[s].push({ t, w });
    }
    const ans: number[] = Array(n).fill(0);
    const dfs = (s: number, mul: number) => {
        ans[s] = mul;
        for (const { t, w } of g[s]) {
            dfs(t, Number((BigInt(mul) * BigInt(w)) % mod));
        }
    };
    dfs(0, 1);
    return ans;
}
