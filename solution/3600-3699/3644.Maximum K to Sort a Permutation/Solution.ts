function sortPermutation(nums: number[]): number {
    let ans = -1;
    for (let i = 0; i < nums.length; ++i) {
        if (i != nums[i]) {
            ans &= nums[i];
        }
    }
    return Math.max(ans, 0);
}
