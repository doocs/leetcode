function findLUSlength(strs: string[]): number {
    const n = strs.length;
    let ans = -1;
    const check = (s: string, t: string): boolean => {
        const [m, n] = [s.length, t.length];
        let i = 0;
        for (let j = 0; i < m && j < n; ++j) {
            if (s[i] === t[j]) {
                ++i;
            }
        }
        return i === m;
    };
    for (let i = 0; i < n; ++i) {
        let x = strs[i].length;
        for (let j = 0; j < n; ++j) {
            if (i !== j && check(strs[i], strs[j])) {
                x = -1;
                break;
            }
        }
        ans = Math.max(ans, x);
    }
    return ans;
}
