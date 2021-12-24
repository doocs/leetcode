function maxSubarraySumCircular(nums: number[]): number {
    let pre1 = nums[0],
        pre2 = nums[0];
    let ans1 = nums[0],
        ans2 = nums[0];
    let sum = nums[0];

    for (let i = 1; i < nums.length; ++i) {
        let cur = nums[i];
        sum += cur;
        pre1 = Math.max(pre1 + cur, cur);
        ans1 = Math.max(pre1, ans1);

        pre2 = Math.min(pre2 + cur, cur);
        ans2 = Math.min(pre2, ans2);
    }
    return ans1 > 0 ? Math.max(ans1, sum - ans2) : ans1;
}
