function longestMonotonicSubarray(nums) {
    const n = nums.length;
    let ans = 1;

    for (let i = 1, t1 = 1, t2 = 1; i < n; i++) {
        t1 = nums[i] > nums[i - 1] ? t1 + 1 : 1;
        t2 = nums[i] < nums[i - 1] ? t2 + 1 : 1;
        ans = Math.max(ans, t1, t2);
    }

    return ans;
}
