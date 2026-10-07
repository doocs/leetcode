function maximumJumps(nums: number[], target: number): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(-(1 << 30));
    f[n - 1] = 0;
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            if (Math.abs(nums[i] - nums[j]) <= target) {
                f[i] = Math.max(f[i], 1 + f[j]);
            }
        }
    }
    return f[0] < 0 ? -1 : f[0];
}
