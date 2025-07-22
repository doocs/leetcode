function minSwaps(nums: number[]): number {
    const n = nums.length;
    const k = nums.reduce((a, b) => a + b, 0);
    let cnt = k - nums.slice(0, k).reduce((a, b) => a + b, 0);
    let min = cnt;

    for (let i = k; i < n + k; i++) {
        cnt += nums[i - k] - nums[i % n];
        min = Math.min(min, cnt);
    }

    return min;
}
