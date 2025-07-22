function numDupDigitsAtMostN(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f = Array.from({ length: m }, () => Array(1 << 10).fill(-1));

    const dfs = (i: number, mask: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i][mask] !== -1) {
            return f[i][mask];
        }
        const up = limit ? parseInt(s[i]) : 9;
        let ans = 0;
        for (let j = 0; j <= up; j++) {
            if (lead && j === 0) {
                ans += dfs(i + 1, mask, true, limit && j === up);
            } else if (((mask >> j) & 1) === 0) {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j === up);
            }
        }
        if (!lead && !limit) {
            f[i][mask] = ans;
        }
        return ans;
    };

    return n - dfs(0, 0, true, true);
}
