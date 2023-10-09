function findMissingRanges(nums: number[], lower: number, upper: number): number[][] {
    const n = nums.length;
    if (n === 0) {
        return [[lower, upper]];
    }
    const ans: number[][] = [];
    if (nums[0] > lower) {
        ans.push([lower, nums[0] - 1]);
    }
    for (let i = 1; i < n; ++i) {
        if (nums[i] - nums[i - 1] > 1) {
            ans.push([nums[i - 1] + 1, nums[i] - 1]);
        }
    }
    if (nums[n - 1] < upper) {
        ans.push([nums[n - 1] + 1, upper]);
    }
    return ans;
}
