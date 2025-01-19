function subarraySum(nums: number[]): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += s[i + 1] - s[Math.max(0, i - nums[i])];
    }
    return ans;
}
