function concatWithReverse(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(2 * n);
    for (let i = 0; i < n; ++i) {
        ans[i] = nums[i];
        ans[i + n] = nums[n - i - 1];
    }
    return ans;
}
