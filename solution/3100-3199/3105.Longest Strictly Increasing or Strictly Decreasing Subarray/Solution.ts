function longestMonotonicSubarray(nums: number[]): number {
    let ans = 1;
    for (let i = 1, t = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    for (let i = 1, t = 1; i < nums.length; ++i) {
        if (nums[i - 1] > nums[i]) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
}
