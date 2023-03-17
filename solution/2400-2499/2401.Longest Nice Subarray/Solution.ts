function longestNiceSubarray(nums: number[]): number {
    let mask = 0;
    let ans = 0;
    for (let i = 0, j = 0; i < nums.length; ++i) {
        while ((mask & nums[i]) !== 0) {
            mask ^= nums[j++];
        }
        ans = Math.max(ans, i - j + 1);
        mask |= nums[i];
    }
    return ans;
}
