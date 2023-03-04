function camelMatch(queries: string[], pattern: string): boolean[] {
    const check = (s: string, t: string) => {
        const m = s.length;
        const n = t.length;
        let i = 0;
        let j = 0;
        for (; j < n; ++i, ++j) {
            while (i < m && s[i] !== t[j] && s[i].codePointAt(0) >= 97) {
                ++i;
            }
            if (i === m || s[i] !== t[j]) {
                return false;
            }
        }
        while (i < m && s[i].codePointAt(0) >= 97) {
            ++i;
        }
        return i == m;
    };
    const ans: boolean[] = [];
    for (const q of queries) {
        ans.push(check(q, pattern));
    }
    return ans;
}
