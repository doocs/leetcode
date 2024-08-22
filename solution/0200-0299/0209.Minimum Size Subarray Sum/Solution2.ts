function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let [s, ans] = [0, n + 1];
    for (let l = 0, r = 0; r < n; ++r) {
        s += nums[r];
        while (s >= target) {
            ans = Math.min(ans, r - l + 1);
            s -= nums[l++];
        }
    }
    return ans > n ? 0 : ans;
}
