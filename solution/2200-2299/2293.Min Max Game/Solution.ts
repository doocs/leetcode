function minMaxGame(nums: number[]): number {
    for (let n = nums.length; n > 1; ) {
        n >>= 1;
        for (let i = 0; i < n; ++i) {
            const a = nums[i << 1];
            const b = nums[(i << 1) | 1];
            nums[i] = i % 2 == 0 ? Math.min(a, b) : Math.max(a, b);
        }
    }
    return nums[0];
}
