function minimumOperations(leaves: string): number {
    const n = leaves.length;
    const inf = 1 << 30;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(3).fill(inf));
    f[0][0] = leaves[0] === 'y' ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (leaves[i] === 'r') {
            f[i][0] = f[i - 1][0];
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + 1;
            f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]);
        } else {
            f[i][0] = f[i - 1][0] + 1;
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]);
            f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]) + 1;
        }
    }
    return f[n - 1][2];
}
