function hasAllCodes(s: string, k: number): boolean {
    const n = s.length;
    const m = 1 << k;
    if (n - k + 1 < m) {
        return false;
    }
    const ss = new Set<string>();
    for (let i = 0; i + k <= n; ++i) {
        ss.add(s.slice(i, i + k));
    }
    return ss.size === m;
}
