function minSpaceWastedKResizing(nums: number[], k: number): number {
    k += 1;
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));

    for (let i = 0; i < n; i++) {
        let s = 0,
            mx = 0;
        for (let j = i; j < n; j++) {
            s += nums[j];
            mx = Math.max(mx, nums[j]);
            g[i][j] = mx * (j - i + 1) - s;
        }
    }

    const inf = Number.POSITIVE_INFINITY;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(inf));
    f[0][0] = 0;

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= k; j++) {
            for (let h = 0; h < i; h++) {
                f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
            }
        }
    }

    return f[n][k];
}
