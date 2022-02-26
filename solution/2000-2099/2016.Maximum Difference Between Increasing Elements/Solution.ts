function maximumDifference(nums: number[]): number {
    const n = nums.length;
    let min = nums[0];
    let res = -1;
    for (let i = 1; i < n; i++) {
        res = Math.max(res, nums[i] - min);
        min = Math.min(min, nums[i]);
    }
    return res === 0 ? -1 : res;
}
