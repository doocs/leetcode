function minCapability(nums: number[], k: number): number {
    const f = (mx: number): boolean => {
        let cnt = 0;
        let j = -2;
        for (let i = 0; i < nums.length; ++i) {
            if (nums[i] <= mx && i - j > 1) {
                ++cnt;
                j = i;
            }
        }
        return cnt >= k;
    };

    let left = 1;
    let right = Math.max(...nums);
    while (left < right) {
        const mid = (left + right) >> 1;
        if (f(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
