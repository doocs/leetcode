function longestSubarray(nums: number[]): number {
    let f = 2;
    let ans = f;
    for (let i = 2; i < nums.length; ++i) {
        if (nums[i] === nums[i - 1] + nums[i - 2]) {
            ans = Math.max(ans, ++f);
        } else {
            f = 2;
        }
    }
    return ans;
}
