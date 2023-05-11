function maxValueAfterReverse(nums: number[]): number {
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n - 1; ++i) {
        s += Math.abs(nums[i] - nums[i + 1]);
    }
    let ans = s;
    for (let i = 0; i < n - 1; ++i) {
        const d = Math.abs(nums[i] - nums[i + 1]);
        ans = Math.max(ans, s + Math.abs(nums[0] - nums[i + 1]) - d);
        ans = Math.max(ans, s + Math.abs(nums[n - 1] - nums[i]) - d);
    }
    const dirs = [1, -1, -1, 1, 1];
    const inf = 1 << 30;
    for (let k = 0; k < 4; ++k) {
        let mx = -inf;
        let mi = inf;
        for (let i = 0; i < n - 1; ++i) {
            const a = dirs[k] * nums[i] + dirs[k + 1] * nums[i + 1];
            const b = Math.abs(nums[i] - nums[i + 1]);
            mx = Math.max(mx, a - b);
            mi = Math.min(mi, a + b);
        }
        ans = Math.max(ans, s + Math.max(0, mx - mi));
    }
    return ans;
}
