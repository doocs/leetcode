function minimumMoves(arr: number[]): number {
    const n = arr.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));

    for (let i = 0; i < n; ++i) {
        f[i][i] = 1;
    }

    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            if (i + 1 === j) {
                f[i][j] = arr[i] === arr[j] ? 1 : 2;
            } else {
                let t = arr[i] === arr[j] ? f[i + 1][j - 1] : Infinity;
                for (let k = i; k < j; ++k) {
                    t = Math.min(t, f[i][k] + f[k + 1][j]);
                }
                f[i][j] = t;
            }
        }
    }

    return f[0][n - 1];
}
