function numberOfSpecialSubstrings(s: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = s.length;
    const cnt: number[] = Array(26).fill(0);
    let ans = 0;
    for (let i = 0, j = 0; i < n; ++i) {
        const k = idx(s[i]);
        ++cnt[k];
        while (cnt[k] > 1) {
            --cnt[idx(s[j++])];
        }
        ans += i - j + 1;
    }
    return ans;
}
