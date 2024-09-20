function rotatedDigits(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(2).fill(-1));
    const dfs = (i: number, ok: number, limit: boolean): number => {
        if (i >= m) {
            return ok;
        }
        if (!limit && f[i][ok] !== -1) {
            return f[i][ok];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if ([0, 1, 8].includes(j)) {
                ans += dfs(i + 1, ok, limit && j === up);
            } else if ([2, 5, 6, 9].includes(j)) {
                ans += dfs(i + 1, 1, limit && j === up);
            }
        }
        if (!limit) {
            f[i][ok] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true);
}
