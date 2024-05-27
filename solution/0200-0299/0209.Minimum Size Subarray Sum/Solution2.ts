function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let s = nums[0];
    let ans = n + 1;
    for (let i = 0, j = 0; i < n; ) {
        if (s >= target) {
            ans = Math.min(ans, i - j + 1);
            s -= nums[j++];
        }
        if (s < target) s += nums[++i];
    }
    return ans === n + 1 ? 0 : ans;
}
