function findSubstringInWraproundString(s: string): number {
    const idx = (c: string): number => c.charCodeAt(0) - 97;
    const f: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0, k = 0; i < n; ++i) {
        const j = idx(s[i]);
        if (i && (j - idx(s[i - 1]) + 26) % 26 === 1) {
            ++k;
        } else {
            k = 1;
        }
        f[j] = Math.max(f[j], k);
    }
    return f.reduce((acc, cur) => acc + cur, 0);
}
