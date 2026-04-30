function firstStableIndex(nums: number[], k: number): number {
    const n = nums.length;
    const right = new Array<number>(n);
    right[n - 1] = nums[n - 1];

    for (let i = n - 2; i >= 0; i--) {
        right[i] = Math.min(right[i + 1], nums[i]);
    }

    let left = 0;
    for (let i = 0; i < n; i++) {
        left = Math.max(left, nums[i]);
        if (left - right[i] <= k) {
            return i;
        }
    }
    return -1;
}
