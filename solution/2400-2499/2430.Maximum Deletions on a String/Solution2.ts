function deleteString(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n).fill(1);
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 1; j <= (n - i) >> 1; ++j) {
            if (s.slice(i, i + j) === s.slice(i + j, i + j + j)) {
                f[i] = Math.max(f[i], f[i + j] + 1);
            }
        }
    }
    return f[0];
}
