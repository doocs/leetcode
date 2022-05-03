function findLengthOfLCIS(nums: number[]): number {
    const n = nums.length;
    let res = 1;
    let i = 0;
    for (let j = 1; j < n; j++) {
        if (nums[j - 1] >= nums[j]) {
            res = Math.max(res, j - i);
            i = j;
        }
    }
    return Math.max(res, n - i);
}
