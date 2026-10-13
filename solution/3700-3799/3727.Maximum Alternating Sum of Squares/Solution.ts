function maxAlternatingSum(nums: number[]): number {
    nums = nums.map(x => x * x).sort((a, b) => a - b);
    let ans = 0;
    const n = nums.length;
    const m = n >> 1;
    for (let i = 0; i < n; i++) {
        ans += i < m ? -nums[i] : nums[i];
    }
    return ans;
}
