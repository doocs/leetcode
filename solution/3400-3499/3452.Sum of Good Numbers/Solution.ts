function sumOfGoodNumbers(nums: number[], k: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (i >= k && nums[i] <= nums[i - k]) {
            continue;
        }
        if (i + k < n && nums[i] <= nums[i + k]) {
            continue;
        }
        ans += nums[i];
    }
    return ans;
}
