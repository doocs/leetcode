function hasAllCodes(s: string, k: number): boolean {
    const n = s.length;
    const m = 1 << k;
    if (n - k + 1 < m) {
        return false;
    }
    let x = +`0b${s.slice(0, k)}`;
    const ss = new Set<number>();
    ss.add(x);
    for (let i = k; i < n; ++i) {
        const a = +s[i - k] << (k - 1);
        const b = +s[i];
        x = ((x - a) << 1) | b;
        ss.add(x);
    }
    return ss.size === m;
}
