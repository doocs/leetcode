function countNumbersWithUniqueDigits(n: number): number {
    const f: number[][] = Array.from({ length: n }, () => Array(1 << 10).fill(-1));
    const dfs = (i: number, mask: number, lead: boolean): number => {
        if (i < 0) {
            return 1;
        }
        if (!lead && f[i][mask] !== -1) {
            return f[i][mask];
        }
        let ans = 0;
        for (let j = 0; j < 10; ++j) {
            if ((mask >> j) & 1) {
                continue;
            }
            if (lead && j === 0) {
                ans += dfs(i - 1, mask, true);
            } else {
                ans += dfs(i - 1, mask | (1 << j), false);
            }
        }
        if (!lead) {
            f[i][mask] = ans;
        }
        return ans;
    };
    return dfs(n - 1, 0, true);
}
