function count(num1: string, num2: string, min_sum: number, max_sum: number): number {
    const mod = 1e9 + 7;
    let f: number[][] = Array(23)
        .fill(0)
        .map(() => Array(220).fill(-1));
    let num = num2;
    const dfs = (pos: number, s: number, limit: boolean): number => {
        if (pos >= num.length) {
            return s >= min_sum && s <= max_sum ? 1 : 0;
        }
        if (!limit && f[pos][s] !== -1) {
            return f[pos][s];
        }
        let ans = 0;
        const up = limit ? +num[pos] : 9;
        for (let i = 0; i <= up; i++) {
            ans = (ans + dfs(pos + 1, s + i, limit && i === up)) % mod;
        }
        if (!limit) {
            f[pos][s] = ans;
        }
        return ans;
    };
    let ans = dfs(0, 0, true);
    num = (BigInt(num1) - 1n).toString();
    f = Array(23)
        .fill(0)
        .map(() => Array(220).fill(-1));
    ans = (ans - dfs(0, 0, true) + mod) % mod;
    return ans;
}
