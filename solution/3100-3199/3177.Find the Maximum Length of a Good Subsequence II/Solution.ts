function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    const mp: Map<number, number>[] = Array.from({ length: k + 1 }, () => new Map());
    const g: number[][] = Array.from({ length: k + 1 }, () => Array(3).fill(0));
    let ans = 0;

    for (let i = 0; i < n; i++) {
        for (let h = 0; h <= k; h++) {
            f[i][h] = mp[h].get(nums[i]) || 0;
            if (h > 0) {
                if (g[h - 1][0] !== nums[i]) {
                    f[i][h] = Math.max(f[i][h], g[h - 1][1]);
                } else {
                    f[i][h] = Math.max(f[i][h], g[h - 1][2]);
                }
            }
            f[i][h]++;
            mp[h].set(nums[i], Math.max(mp[h].get(nums[i]) || 0, f[i][h]));
            if (g[h][0] !== nums[i]) {
                if (f[i][h] >= g[h][1]) {
                    g[h][2] = g[h][1];
                    g[h][1] = f[i][h];
                    g[h][0] = nums[i];
                } else {
                    g[h][2] = Math.max(g[h][2], f[i][h]);
                }
            } else {
                g[h][1] = Math.max(g[h][1], f[i][h]);
            }
            ans = Math.max(ans, f[i][h]);
        }
    }

    return ans;
}
