function atMostNGivenDigitSet(digits: string[], n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[] = Array(m).fill(-1);
    const nums = new Set<number>(digits.map(d => parseInt(d)));
    const dfs = (i: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i] !== -1) {
            return f[i];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if (!j && lead) {
                ans += dfs(i + 1, true, limit && j === up);
            } else if (nums.has(j)) {
                ans += dfs(i + 1, false, limit && j === up);
            }
        }
        if (!lead && !limit) {
            f[i] = ans;
        }
        return ans;
    };
    return dfs(0, true, true);
}
