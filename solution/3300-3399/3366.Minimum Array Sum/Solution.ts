function minArraySum(nums: number[], d: number, op1: number, op2: number): number {
    const n = nums.length;
    const inf = Number.MAX_SAFE_INTEGER;

    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: op1 + 1 }, () => Array(op2 + 1).fill(inf)),
    );
    f[0][0][0] = 0;

    for (let i = 1; i <= n; i++) {
        const x = nums[i - 1];
        for (let j = 0; j <= op1; j++) {
            for (let k = 0; k <= op2; k++) {
                f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k] + x);
                if (j > 0) {
                    f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - 1][k] + Math.floor((x + 1) / 2));
                }
                if (k > 0 && x >= d) {
                    f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k - 1] + (x - d));
                }
                if (j > 0 && k > 0) {
                    const y = Math.floor((x + 1) / 2);
                    if (y >= d) {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - 1][k - 1] + (y - d));
                    }
                    if (x >= d) {
                        f[i][j][k] = Math.min(
                            f[i][j][k],
                            f[i - 1][j - 1][k - 1] + Math.floor((x - d + 1) / 2),
                        );
                    }
                }
            }
        }
    }

    let ans = inf;
    for (let j = 0; j <= op1; j++) {
        for (let l = 0; l <= op2; l++) {
            ans = Math.min(ans, f[n][j][l]);
        }
    }
    return ans;
}
