function maxScore(nums: number[], x: number): number {
    const inf = 1 << 30;
    const f: number[] = Array(2).fill(-inf);
    f[nums[0] & 1] = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        f[nums[i] & 1] = Math.max(f[nums[i] & 1] + nums[i], f[(nums[i] & 1) ^ 1] + nums[i] - x);
    }
    return Math.max(f[0], f[1]);
}
