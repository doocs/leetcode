function largestEvenSum(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < k; ++i) {
        ans += nums[n - i - 1];
    }
    if (ans % 2 === 0) {
        return ans;
    }
    const inf = 1 << 29;
    let mx1 = -inf,
        mx2 = -inf;
    for (let i = 0; i < n - k; ++i) {
        if (nums[i] % 2 === 1) {
            mx1 = nums[i];
        } else {
            mx2 = nums[i];
        }
    }
    let mi1 = inf,
        mi2 = inf;
    for (let i = n - 1; i >= n - k; --i) {
        if (nums[i] % 2 === 1) {
            mi2 = nums[i];
        } else {
            mi1 = nums[i];
        }
    }
    ans = Math.max(ans - mi1 + mx1, ans - mi2 + mx2);
    return ans < 0 ? -1 : ans;
}
