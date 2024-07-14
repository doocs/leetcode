function longestSubarray(nums: number[]): number {
    const n = nums.length;
    const left: number[] = Array(n + 1).fill(0);
    const right: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        if (nums[i - 1]) {
            left[i] = left[i - 1] + 1;
        }
    }
    for (let i = n - 1; ~i; --i) {
        if (nums[i]) {
            right[i] = right[i + 1] + 1;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, left[i] + right[i + 1]);
    }
    return ans;
}
