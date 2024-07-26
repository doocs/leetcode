function longestSemiRepetitiveSubstring(s: string): number {
    const n = s.length;
    let [cnt, l] = [0, 0];
    for (let i = 1; i < n; ++i) {
        cnt += s[i] === s[i - 1] ? 1 : 0;
        if (cnt > 1) {
            cnt -= s[l] === s[l + 1] ? 1 : 0;
            ++l;
        }
    }
    return n - l;
}
