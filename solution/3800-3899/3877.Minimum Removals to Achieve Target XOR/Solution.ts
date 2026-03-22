function minRemovals(nums: number[], target: number): number {
    let mx = Math.max(...nums);

    let m = 0;
    while (1 << m <= mx) {
        m++;
    }
    if (1 << m <= target) {
        return -1;
    }

    const n = nums.length;
    const f = Array.from({ length: n + 1 }, () => Array(1 << m).fill(-Infinity));

    f[0][0] = 0;

    for (let i = 1; i <= n; i++) {
        const x = nums[i - 1];
        for (let j = 0; j < 1 << m; j++) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][j ^ x] + 1);
        }
    }

    if (f[n][target] < 0) {
        return -1;
    }
    return n - f[n][target];
}
