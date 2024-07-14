function takeCharacters(s: string, k: number): number {
    const idx = (c: string) => c.charCodeAt(0) - 97;
    const cnt: number[] = Array(3).fill(0);
    for (const c of s) {
        ++cnt[idx(c)];
    }
    if (cnt.some(v => v < k)) {
        return -1;
    }
    const n = s.length;
    let [mx, j] = [0, 0];
    for (let i = 0; i < n; ++i) {
        const c = idx(s[i]);
        --cnt[c];
        while (cnt[c] < k) {
            ++cnt[idx(s[j++])];
        }
        mx = Math.max(mx, i - j + 1);
    }
    return n - mx;
}
