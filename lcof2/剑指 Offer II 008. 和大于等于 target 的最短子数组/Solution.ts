function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    const inf = 1 << 30;
    let ans = inf;
    let s = 0;
    for (let i = 0, j = 0; j < n; ++j) {
        s += nums[j];
        while (s >= target) {
            ans = Math.min(ans, j - i + 1);
            s -= nums[i++];
        }
    }
    return ans === inf ? 0 : ans;
}
