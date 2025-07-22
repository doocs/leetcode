function stoneGameV(stoneValue: number[]): number {
    const n = stoneValue.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + stoneValue[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(-1));

    const dfs = (i: number, j: number): number => {
        if (i >= j) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        let [ans, l, r] = [0, 0, s[j + 1] - s[i]];
        for (let k = i; k < j; ++k) {
            l += stoneValue[k];
            r -= stoneValue[k];
            if (l < r) {
                if (ans > l * 2) {
                    continue;
                }
                ans = Math.max(ans, l + dfs(i, k));
            } else if (l > r) {
                if (ans > r * 2) {
                    break;
                }
                ans = Math.max(ans, r + dfs(k + 1, j));
            } else {
                ans = Math.max(ans, l + dfs(i, k), r + dfs(k + 1, j));
            }
        }
        return (f[i][j] = ans);
    };

    return dfs(0, n - 1);
}
