function maximumMedianSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = n / 3; i < n; i += 2) {
        ans += nums[i];
    }
    return ans;
}
