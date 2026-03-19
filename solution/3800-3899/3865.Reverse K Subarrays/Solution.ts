function reverseSubarrays(nums: number[], k: number): number[] {
    const n = nums.length;
    const m = Math.floor(n / k);
    for (let i = 0; i < n; i += m) {
        let l = i,
            r = i + m - 1;
        while (l < r) {
            const t = nums[l];
            nums[l++] = nums[r];
            nums[r--] = t;
        }
    }
    return nums;
}
