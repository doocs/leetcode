function minimizeMax(nums: number[], p: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let l = 0,
        r = nums[n - 1] - nums[0] + 1;
    const check = (diff: number): boolean => {
        let cnt = 0;
        for (let i = 0; i < n - 1; ++i) {
            if (nums[i + 1] - nums[i] <= diff) {
                ++cnt;
                ++i;
            }
        }
        return cnt >= p;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
