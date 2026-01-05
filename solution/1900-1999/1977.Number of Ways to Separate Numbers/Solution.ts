function numberOfCombinations(num: string): number {
    const n: number = num.length;
    const mod: number = 1_000_000_007;

    const lcp: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    const dp: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));

    for (let i = n - 1; i >= 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (num[i] === num[j]) {
                lcp[i][j] = 1 + lcp[i + 1][j + 1];
            }
        }
    }

    function cmp(i: number, j: number, k: number): boolean {
        const x: number = lcp[i][j];
        return x >= k || num[i + x] >= num[j + x];
    }

    dp[0][0] = 1;

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= i; j++) {
            let v: number = 0;
            if (num[i - j] !== '0') {
                if (i - j - j >= 0 && cmp(i - j, i - j - j, j)) {
                    v = dp[i - j][j];
                } else {
                    v = dp[i - j][Math.min(j - 1, i - j)];
                }
            }
            dp[i][j] = (dp[i][j - 1] + v) % mod;
        }
    }

    return dp[n][n];
}
