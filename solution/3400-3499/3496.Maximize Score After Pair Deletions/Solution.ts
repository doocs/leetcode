function maxScore(nums: number[]): number {
    const inf = Infinity;
    const n = nums.length;
    let [s, mi, t] = [0, inf, inf];
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        mi = Math.min(mi, nums[i]);
        if (i + 1 < n) {
            t = Math.min(t, nums[i] + nums[i + 1]);
        }
    }
    return n % 2 ? s - mi : s - t;
}
