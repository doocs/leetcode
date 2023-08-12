function encode(s: string): string {
    const n = s.length;
    const f: string[][] = new Array(n).fill(0).map(() => new Array(n).fill(''));
    const g = (i: number, j: number): string => {
        const t = s.slice(i, j + 1);
        if (t.length < 5) {
            return t;
        }
        const k = t.repeat(2).indexOf(t, 1);
        if (k < t.length) {
            const cnt = Math.floor(t.length / k);
            return cnt + '[' + f[i][i + k - 1] + ']';
        }
        return t;
    };
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i; j < n; ++j) {
            f[i][j] = g(i, j);
            if (j - i + 1 > 4) {
                for (let k = i; k < j; ++k) {
                    const t = f[i][k] + f[k + 1][j];
                    if (t.length < f[i][j].length) {
                        f[i][j] = t;
                    }
                }
            }
        }
    }
    return f[0][n - 1];
}
