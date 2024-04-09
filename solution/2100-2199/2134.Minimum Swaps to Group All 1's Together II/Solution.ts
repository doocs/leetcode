function minSwaps(nums: number[]): number {
    const k = nums.reduce((a, b) => a + b, 0);
    let cnt = nums.slice(0, k).reduce((a, b) => a + b, 0);
    let mx = cnt;
    const n = nums.length;
    for (let i = k; i < n + k; ++i) {
        cnt += nums[i % n] - nums[(i - k + n) % n];
        mx = Math.max(mx, cnt);
    }
    return k - mx;
}
