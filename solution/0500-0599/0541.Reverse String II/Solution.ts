function reverseStr(s: string, k: number): string {
    const n = s.length;
    const cs = s.split('');
    for (let i = 0; i < n; i += 2 * k) {
        for (let l = i, r = Math.min(i + k - 1, n - 1); l < r; l++, r--) {
            [cs[l], cs[r]] = [cs[r], cs[l]];
        }
    }
    return cs.join('');
}
