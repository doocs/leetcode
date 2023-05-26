function twoSumLessThanK(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = -1;
    for (let i = 0, j = nums.length - 1; i < j; ) {
        const s = nums[i] + nums[j];
        if (s < k) {
            ans = Math.max(ans, s);
            ++i;
        } else {
            --j;
        }
    }
    return ans;
}
