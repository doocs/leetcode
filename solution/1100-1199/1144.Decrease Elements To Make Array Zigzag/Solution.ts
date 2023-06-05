function movesToMakeZigzag(nums: number[]): number {
    const ans: number[] = Array(2).fill(0);
    const n = nums.length;
    for (let i = 0; i < 2; ++i) {
        for (let j = i; j < n; j += 2) {
            let d = 0;
            if (j > 0) {
                d = Math.max(d, nums[j] - nums[j - 1] + 1);
            }
            if (j < n - 1) {
                d = Math.max(d, nums[j] - nums[j + 1] + 1);
            }
            ans[i] += d;
        }
    }
    return Math.min(...ans);
}
