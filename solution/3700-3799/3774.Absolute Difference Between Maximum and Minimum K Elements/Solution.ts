function absDifference(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        ans += nums.at(-i - 1)! - nums[i];
    }
    return ans;
}
