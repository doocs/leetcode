function minimumRightShifts(nums: number[]): number {
    const n = nums.length;
    let i = 1;
    while (i < n && nums[i - 1] < nums[i]) {
        ++i;
    }
    let k = i + 1;
    while (k < n && nums[k - 1] < nums[k] && nums[k] < nums[0]) {
        ++k;
    }
    return k < n ? -1 : n - i;
}
