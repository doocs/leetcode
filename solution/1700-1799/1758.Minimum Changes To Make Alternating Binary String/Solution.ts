function minOperations(s: string): number {
    let cnt = 0;
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s[i] !== '01'[i & 1]) {
            ++cnt;
        }
    }
    return Math.min(cnt, n - cnt);
}
