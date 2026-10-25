function peakValleyDifference(nums: number[]): number {
    let ans = 0;
    for (let i = 1; i < nums.length - 1; i++) {
        if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
            ans += nums[i];
        } else if (nums[i - 1] > nums[i] && nums[i] < nums[i + 1]) {
            ans -= nums[i];
        }
    }
    return ans;
}
