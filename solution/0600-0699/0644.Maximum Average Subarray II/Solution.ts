function findMaxAverage(nums: number[], k: number): number {
    const eps = 1e-5;
    let l = Math.min(...nums);
    let r = Math.max(...nums);
    const check = (v: number): boolean => {
        let s = nums.slice(0, k).reduce((a, b) => a + b) - v * k;
        if (s >= 0) {
            return true;
        }
        let t = 0;
        let mi = 0;
        for (let i = k; i < nums.length; ++i) {
            s += nums[i] - v;
            t += nums[i - k] - v;
            mi = Math.min(mi, t);
            if (s >= mi) {
                return true;
            }
        }
        return false;
    };
    while (r - l >= eps) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    return l;
}
