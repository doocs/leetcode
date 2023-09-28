function countWays(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i <= n; ++i) {
        if ((i && nums[i - 1] >= i) || (i < n && nums[i] <= i)) {
            continue;
        }
        ++ans;
    }
    return ans;
}
