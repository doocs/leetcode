function minXor(nums: number[], k: number): number {
    const n = nums.length;
    const g: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        g[i] = g[i - 1] ^ nums[i - 1];
    }

    const inf = Number.MAX_SAFE_INTEGER;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(inf));
    f[0][0] = 0;

    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= Math.min(i, k); ++j) {
            for (let h = j - 1; h < i; ++h) {
                f[i][j] = Math.min(f[i][j], Math.max(f[h][j - 1], g[i] ^ g[h]));
            }
        }
    }

    return f[n][k];
}
