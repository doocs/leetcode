function findLengthOfLCIS(nums: number[]): number {
    let ans = 1;
    const n = nums.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && nums[j - 1] < nums[j]) {
            ++j;
        }
        ans = Math.max(ans, j - i);
        i = j;
    }
    return ans;
}
