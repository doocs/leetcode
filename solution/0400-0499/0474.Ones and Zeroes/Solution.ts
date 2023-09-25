function findMaxForm(strs: string[], m: number, n: number): number {
    const f = Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => 0));
    const count = (s: string): [number, number] => {
        let a = 0;
        for (const c of s) {
            a += c === '0' ? 1 : 0;
        }
        return [a, s.length - a];
    };
    for (const s of strs) {
        const [a, b] = count(s);
        for (let i = m; i >= a; --i) {
            for (let j = n; j >= b; --j) {
                f[i][j] = Math.max(f[i][j], f[i - a][j - b] + 1);
            }
        }
    }
    return f[m][n];
}
