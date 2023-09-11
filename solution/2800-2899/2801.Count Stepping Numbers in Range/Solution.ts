function countSteppingNumbers(low: string, high: string): number {
    const mod = 1e9 + 7;
    const m = high.length;
    let f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(10).fill(-1));
    let num = high;
    const dfs = (pos: number, pre: number, lead: boolean, limit: boolean): number => {
        if (pos >= num.length) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[pos][pre] !== -1) {
            return f[pos][pre];
        }
        let ans = 0;
        const up = limit ? +num[pos] : 9;
        for (let i = 0; i <= up; i++) {
            if (i == 0 && lead) {
                ans += dfs(pos + 1, pre, true, limit && i == up);
            } else if (pre == -1 || Math.abs(pre - i) == 1) {
                ans += dfs(pos + 1, i, false, limit && i == up);
            }
            ans %= mod;
        }
        if (!lead && !limit) {
            f[pos][pre] = ans;
        }
        return ans;
    };
    const a = dfs(0, -1, true, true);
    num = (BigInt(low) - 1n).toString();
    f = Array(m + 1)
        .fill(0)
        .map(() => Array(10).fill(-1));
    const b = dfs(0, -1, true, true);
    return (a - b + mod) % mod;
}
