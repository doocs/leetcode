function largestNumber(cost: number[], target: number): string {
    const inf = 1 << 30;
    const f: number[][] = Array(10)
        .fill(0)
        .map(() => Array(target + 1).fill(-inf));
    const g: number[][] = Array(10)
        .fill(0)
        .map(() => Array(target + 1).fill(0));
    f[0][0] = 0;
    for (let i = 1; i <= 9; ++i) {
        const c = cost[i - 1];
        for (let j = 0; j <= target; ++j) {
            if (j < c || f[i][j - c] + 1 < f[i - 1][j]) {
                f[i][j] = f[i - 1][j];
                g[i][j] = j;
            } else {
                f[i][j] = f[i][j - c] + 1;
                g[i][j] = j - c;
            }
        }
    }
    if (f[9][target] < 0) {
        return '0';
    }
    const ans: number[] = [];
    for (let i = 9, j = target; i; ) {
        if (g[i][j] === j) {
            --i;
        } else {
            ans.push(i);
            j = g[i][j];
        }
    }
    return ans.join('');
}
