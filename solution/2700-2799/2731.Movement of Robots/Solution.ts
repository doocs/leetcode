function sumDistance(nums: number[], s: string, d: number): number {
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        nums[i] += s[i] === 'L' ? -d : d;
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    let sum = 0;
    const mod = 1e9 + 7;
    for (let i = 0; i < n; ++i) {
        ans = (ans + i * nums[i] - sum) % mod;
        sum += nums[i];
    }
    return ans;
}
