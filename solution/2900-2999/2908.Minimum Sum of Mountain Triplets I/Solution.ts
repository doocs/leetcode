function minimumSum(nums: number[]): number {
    const n = nums.length;
    const right: number[] = Array(n + 1).fill(Infinity);
    for (let i = n - 1; ~i; --i) {
        right[i] = Math.min(right[i + 1], nums[i]);
    }
    let [ans, left] = [Infinity, Infinity];
    for (let i = 0; i < n; ++i) {
        if (left < nums[i] && right[i + 1] < nums[i]) {
            ans = Math.min(ans, left + nums[i] + right[i + 1]);
        }
        left = Math.min(left, nums[i]);
    }
    return ans === Infinity ? -1 : ans;
}
