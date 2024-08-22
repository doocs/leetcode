function findContestMatch(n: number): string {
    const s: string[] = Array.from({ length: n }, (_, i) => (i + 1).toString());
    for (; n > 1; n >>= 1) {
        for (let i = 0; i < n >> 1; ++i) {
            s[i] = `(${s[i]},${s[n - i - 1]})`;
        }
    }
    return s[0];
}
