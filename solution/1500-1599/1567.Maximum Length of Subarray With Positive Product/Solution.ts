function getMaxLen(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    const g: number[] = Array(n).fill(0);

    if (nums[0] > 0) {
        f[0] = 1;
    }
    if (nums[0] < 0) {
        g[0] = 1;
    }

    let ans = f[0];
    for (let i = 1; i < n; i++) {
        if (nums[i] > 0) {
            f[i] = f[i - 1] + 1;
            g[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
        } else if (nums[i] < 0) {
            f[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
            g[i] = f[i - 1] + 1;
        }

        ans = Math.max(ans, f[i]);
    }

    return ans;
}
