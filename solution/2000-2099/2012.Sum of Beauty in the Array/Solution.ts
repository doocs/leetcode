function sumOfBeauties(nums: number[]): number {
    const n = nums.length;
    const right: number[] = Array(n).fill(nums[n - 1]);
    for (let i = n - 2; i; --i) {
        right[i] = Math.min(right[i + 1], nums[i]);
    }
    let ans = 0;
    for (let i = 1, l = nums[0]; i < n - 1; ++i) {
        const r = right[i + 1];
        if (l < nums[i] && nums[i] < r) {
            ans += 2;
        } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
            ans += 1;
        }
        l = Math.max(l, nums[i]);
    }
    return ans;
}
