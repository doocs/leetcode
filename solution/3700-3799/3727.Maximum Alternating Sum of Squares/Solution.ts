function maxAlternatingSum(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i < n; i++) {
        nums[i] = nums[i] ** 2;
    }
    nums.sort((a, b) => a - b);
    const m = Math.floor(n / 2);
    let ans = 0;
    for (let i = 0; i < m; i++) {
        ans -= nums[i];
    }
    for (let i = m; i < n; i++) {
        ans += nums[i];
    }
    return ans;
}
