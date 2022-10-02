function maxAscendingSum(nums: number[]): number {
    let ans = 0;
    let t = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (i == 0 || nums[i] > nums[i - 1]) {
            t += nums[i];
            ans = Math.max(ans, t);
        } else {
            t = nums[i];
        }
    }
    return ans;
}
