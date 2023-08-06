function numSubarrayProductLessThanK(nums: number[], k: number): number {
    let s = 1;
    let ans = 0;
    const n = nums.length;
    for (let i = 0, j = 0; j < n; ++j) {
        s *= nums[j];
        while (i <= j && s >= k) {
            s /= nums[i++];
        }
        ans += j - i + 1;
    }
    return ans;
}
