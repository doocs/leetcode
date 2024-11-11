function maxIncreasingSubarrays(nums: number[]): number {
    let [ans, pre, cur] = [0, 0, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        ++cur;
        if (i === n - 1 || nums[i] >= nums[i + 1]) {
            ans = Math.max(ans, (cur / 2) | 0, Math.min(pre, cur));
            [pre, cur] = [cur, 0];
        }
    }
    return ans;
}
