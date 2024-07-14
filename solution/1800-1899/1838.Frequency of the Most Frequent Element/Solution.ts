function maxFrequency(nums: number[], k: number): number {
    const n = nums.length;
    nums.sort((a, b) => a - b);
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        s[i] = s[i - 1] + nums[i - 1];
    }
    let [l, r] = [1, n];
    const check = (m: number): boolean => {
        for (let i = m; i <= n; ++i) {
            if (nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
                return true;
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
