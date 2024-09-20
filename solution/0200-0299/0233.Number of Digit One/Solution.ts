function countDigitOne(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(m).fill(-1));
    const dfs = (i: number, cnt: number, limit: boolean): number => {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i][cnt] !== -1) {
            return f[i][cnt];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            ans += dfs(i + 1, cnt + (j === 1 ? 1 : 0), limit && j === up);
        }
        if (!limit) {
            f[i][cnt] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true);
}
