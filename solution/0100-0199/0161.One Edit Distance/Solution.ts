function isOneEditDistance(s: string, t: string): boolean {
    const [m, n] = [s.length, t.length];
    if (m < n) {
        return isOneEditDistance(t, s);
    }
    if (m - n > 1) {
        return false;
    }
    for (let i = 0; i < n; ++i) {
        if (s[i] !== t[i]) {
            return s.slice(i + 1) === t.slice(i + (m === n ? 1 : 0));
        }
    }
    return m === n + 1;
}
