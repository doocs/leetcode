function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let h = 0; h <= k; ++h) {
            for (let j = 0; j < i; ++j) {
                if (nums[i] === nums[j]) {
                    f[i][h] = Math.max(f[i][h], f[j][h]);
                } else if (h) {
                    f[i][h] = Math.max(f[i][h], f[j][h - 1]);
                }
            }
            ++f[i][h];
        }
        ans = Math.max(ans, f[i][k]);
    }
    return ans;
}
