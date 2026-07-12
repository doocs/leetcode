function queryConversions(conversions: number[][], queries: number[][]): number[] {
    const mod = BigInt(1e9 + 7);
    const n = conversions.length + 1;

    const g: { t: number; w: number }[][] = Array.from({ length: n }, () => []);
    for (const [s, t, w] of conversions) {
        g[s].push({ t, w });
    }

    const res: number[] = Array(n).fill(0);

    const dfs = (s: number, mul: number): void => {
        res[s] = mul;
        for (const { t, w } of g[s]) {
            dfs(t, Number((BigInt(mul) * BigInt(w)) % mod));
        }
    };
    dfs(0, 1);

    const qpow = (x: number, n: number): number => {
        let res = 1n;
        let a = BigInt(x);
        while (n > 0) {
            if (n & 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            n >>= 1;
        }
        return Number(res);
    };

    const ans: number[] = [];
    for (const [x, y] of queries) {
        ans.push(Number((BigInt(res[y]) * BigInt(qpow(res[x], 1e9 + 5))) % mod));
    }
    return ans;
}
