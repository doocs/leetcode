function findIntegers(n: number): number {
    const m = n.toString(2).length;
    const f: number[][] = Array.from({ length: m }, () => Array(2).fill(-1));
    const dfs = (i: number, pre: number, limit: boolean): number => {
        if (i < 0) {
            return 1;
        }
        if (!limit && f[i][pre] !== -1) {
            return f[i][pre];
        }
        const up = limit ? (n >> i) & 1 : 1;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if (pre === 1 && j === 1) {
                continue;
            }
            ans += dfs(i - 1, j, limit && j === up);
        }
        if (!limit) {
            f[i][pre] = ans;
        }
        return ans;
    };
    return dfs(m - 1, 0, true);
}
