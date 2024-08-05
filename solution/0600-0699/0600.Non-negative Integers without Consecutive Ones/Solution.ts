function findIntegers(n: number): number {
    const s = n.toString(2);
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => [-1, -1]);

    function dfs(pos: number, pre: number, limit: boolean): number {
        if (pos >= m) {
            return 1;
        }
        if (!limit && f[pos][pre] !== -1) {
            return f[pos][pre];
        }
        const up = limit ? parseInt(s[pos]) : 1;
        let ans = 0;
        for (let i = 0; i <= up; ++i) {
            if (!(pre === 1 && i === 1)) {
                ans += dfs(pos + 1, i, limit && i === up);
            }
        }
        if (!limit) {
            f[pos][pre] = ans;
        }
        return ans;
    }

    return dfs(0, 0, true);
}
