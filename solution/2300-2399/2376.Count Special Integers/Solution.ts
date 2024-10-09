function countSpecialNumbers(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(1 << 10).fill(-1));
    const dfs = (i: number, mask: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!limit && !lead && f[i][mask] !== -1) {
            return f[i][mask];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if ((mask >> j) & 1) {
                continue;
            }
            if (lead && j === 0) {
                ans += dfs(i + 1, mask, true, limit && j === up);
            } else {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j === up);
            }
        }
        if (!limit && !lead) {
            f[i][mask] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true, true);
}
