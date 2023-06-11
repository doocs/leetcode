function longestSemiRepetitiveSubstring(s: string): number {
    const n = s.length;
    let ans = 0;
    for (let i = 0, j = 0, cnt = 0; i < n; ++i) {
        if (i > 0 && s[i] === s[i - 1]) {
            ++cnt;
        }
        while (cnt > 1) {
            if (s[j] === s[j + 1]) {
                --cnt;
            }
            ++j;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
