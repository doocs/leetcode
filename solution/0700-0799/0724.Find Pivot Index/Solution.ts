function pivotIndex(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    for (let i = 0; i < nums.length; ++i) {
        r -= nums[i];
        if (l == r) {
            return i;
        }
        l += nums[i];
    }
    return -1;
}
