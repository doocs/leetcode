function sumOfBeauties(nums: number[]): number {
    let n = nums.length;
    let prefix = new Array(n),
        postfix = new Array(n);
    prefix[0] = nums[0];
    postfix[n - 1] = nums[n - 1];
    for (let i = 1, j = n - 2; i < n; ++i, --j) {
        prefix[i] = Math.max(nums[i], prefix[i - 1]);
        postfix[j] = Math.min(nums[j], postfix[j + 1]);
    }
    let ans = 0;
    for (let i = 1; i < n - 1; ++i) {
        if (prefix[i - 1] < nums[i] && nums[i] < postfix[i + 1]) {
            ans += 2;
        } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
            ans += 1;
        }
    }
    return ans;
}
