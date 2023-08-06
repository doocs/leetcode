function pivotIndex(nums: number[]): number {
    let left = 0;
    let right = nums.reduce((a, b) => a + b, 0);
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        right -= nums[i];
        if (left === right) {
            return i;
        }
        left += nums[i];
    }
    return -1;
}
