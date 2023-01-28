function getSumAbsoluteDifferences(nums: number[]): number[] {
    const s = nums.reduce((a, b) => a + b);
    let t = 0;
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const v = nums[i] * i - t + s - t - nums[i] * (n - i);
        ans[i] = v;
        t += nums[i];
    }
    return ans;
}
