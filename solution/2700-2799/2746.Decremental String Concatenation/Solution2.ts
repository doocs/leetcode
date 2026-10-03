function minimizeConcatenatedLength(words: string[]): number {
    const n = words.length;
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: 26 }, () => Array(26).fill(0)),
    );
    for (let i = n - 1; i > 0; --i) {
        const s = words[i];
        const m = s.length;
        const c = s.charCodeAt(0) - 97;
        const d = s.charCodeAt(m - 1) - 97;
        for (let a = 0; a < 26; ++a) {
            for (let b = 0; b < 26; ++b) {
                const x = f[i + 1][a][d] - (c === b ? 1 : 0);
                const y = f[i + 1][c][b] - (d === a ? 1 : 0);
                f[i][a][b] = m + Math.min(x, y);
            }
        }
    }
    const a = words[0].charCodeAt(0) - 97;
    const b = words[0].charCodeAt(words[0].length - 1) - 97;
    return words[0].length + f[1][a][b];
}
