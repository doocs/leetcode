function minOperations(nums: number[], x: number): number {
    x = nums.reduce((a, b) => a + b, 0) - x;
    const n = nums.length;
    let ans = 1 << 30;
    for (let i = 0, j = 0, s = 0; i < n; ++i) {
        s += nums[i];
        while (j <= i && s > x) {
            s -= nums[j++];
        }
        if (s == x) {
            ans = Math.min(ans, n - (i - j + 1));
        }
    }
    return ans == 1 << 30 ? -1 : ans;
}
