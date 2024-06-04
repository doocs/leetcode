function characterReplacement(s: string, k: number): number {
    const idx = (c: string) => c.charCodeAt(0) - 65;
    const cnt: number[] = Array(26).fill(0);
    const n = s.length;
    let [l, mx] = [0, 0];
    for (let r = 0; r < n; ++r) {
        mx = Math.max(mx, ++cnt[idx(s[r])]);
        if (r - l + 1 - mx > k) {
            --cnt[idx(s[l++])];
        }
    }
    return n - l;
}
