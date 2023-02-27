function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let window = 0;
    const n = nums.length;
    for (let l = 0, r = 1; r < n; ++r) {
        window += (nums[r] - nums[r - 1]) * (r - l);
        while (window > k) {
            window -= nums[r] - nums[l++];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
