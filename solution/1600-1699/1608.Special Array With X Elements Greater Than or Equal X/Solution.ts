function specialArray(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i <= n; i++) {
        if (i === nums.reduce((r, v) => r + (v >= i ? 1 : 0), 0)) {
            return i;
        }
    }
    return -1;
}
