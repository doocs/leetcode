function alternatingSubarray(nums: number[]): number {
    let ans = -1;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let k = 1;
        let j = i;
        for (; j + 1 < n && nums[j + 1] - nums[j] === k; ++j) {
            k *= -1;
        }
        if (j - i + 1 > 1) {
            ans = Math.max(ans, j - i + 1);
        }
    }
    return ans;
}
