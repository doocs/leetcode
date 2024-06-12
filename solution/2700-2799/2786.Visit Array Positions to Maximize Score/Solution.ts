function maxScore(nums: number[], x: number): number {
    const f: number[] = Array(2).fill(-Infinity);
    f[nums[0] & 1] = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        const v = nums[i];
        f[v & 1] = Math.max(f[v & 1], f[(v & 1) ^ 1] - x) + v;
    }
    return Math.max(...f);
}
