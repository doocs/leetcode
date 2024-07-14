function minimumAverage(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = Infinity;
    for (let i = 0; i * 2 < n; ++i) {
        ans = Math.min(ans, nums[i] + nums[n - 1 - i]);
    }
    return ans / 2;
}
