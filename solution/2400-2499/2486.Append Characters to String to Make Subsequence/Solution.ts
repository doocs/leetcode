function appendCharacters(s: string, t: string): number {
    const [m, n] = [s.length, t.length];
    for (let i = 0, j = 0; j < n; ++j) {
        while (i < m && s[i] !== t[j]) {
            ++i;
        }
        if (i === m) {
            return n - j;
        }
        ++i;
    }
    return 0;
}
