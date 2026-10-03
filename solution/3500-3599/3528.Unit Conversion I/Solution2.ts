function baseUnitConversions(conversions: number[][]): number[] {
    const mod = BigInt(1e9 + 7);
    const n = conversions.length + 1;
    const g: { t: number; w: number }[][] = Array.from({ length: n }, () => []);
    for (const [s, t, w] of conversions) {
        g[s].push({ t, w });
    }
    const ans: number[] = Array(n).fill(0);
    const stk: [number, number][] = [[0, 1]];
    while (stk.length) {
        const [s, mul] = stk.pop()!;
        ans[s] = mul;
        for (const { t, w } of g[s]) {
            stk.push([t, Number((BigInt(mul) * BigInt(w)) % mod)]);
        }
    }
    return ans;
}
