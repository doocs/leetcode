function wiggleMaxLength(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(1);
    const g: number[] = Array(n).fill(1);
    let ans = 1;
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[i] > nums[j]) {
                f[i] = Math.max(f[i], g[j] + 1);
            } else if (nums[i] < nums[j]) {
                g[i] = Math.max(g[i], f[j] + 1);
            }
        }
        ans = Math.max(ans, f[i], g[i]);
    }
    return ans;
}
