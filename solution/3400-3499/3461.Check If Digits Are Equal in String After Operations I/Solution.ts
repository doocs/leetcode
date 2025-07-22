function hasSameDigits(s: string): boolean {
    const t = s.split('').map(Number);
    const n = t.length;
    for (let k = n - 1; k > 1; --k) {
        for (let i = 0; i < k; ++i) {
            t[i] = (t[i] + t[i + 1]) % 10;
        }
    }
    return t[0] === t[1];
}
