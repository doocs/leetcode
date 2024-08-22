function findValueOfPartition(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = Infinity;
    for (let i = 1; i < nums.length; ++i) {
        ans = Math.min(ans, Math.abs(nums[i] - nums[i - 1]));
    }
    return ans;
}
