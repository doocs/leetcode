function canBeIncreasing(nums: number[]): boolean {
    const n = nums.length;
    const check = (k: number): boolean => {
        let pre = 0;
        for (let i = 0; i < n; ++i) {
            if (i === k) {
                continue;
            }
            if (pre >= nums[i]) {
                return false;
            }
            pre = nums[i];
        }
        return true;
    };
    let i = 0;
    while (i + 1 < n && nums[i] < nums[i + 1]) {
        ++i;
    }
    return check(i) || check(i + 1);
}
