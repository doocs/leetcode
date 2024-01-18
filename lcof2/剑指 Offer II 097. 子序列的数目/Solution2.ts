function numDistinct(s: string, t: string): number {
    const n = t.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (const a of s) {
        for (let j = n; j; --j) {
            const b = t[j - 1];
            if (a === b) {
                f[j] += f[j - 1];
            }
        }
    }
    return f[n];
}
