function maximumScore(nums: number[]): number {
    const n = nums.length;
    const suf: number[] = new Array(n);
    suf[n - 1] = nums[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        suf[i] = Math.min(nums[i], suf[i + 1]);
    }
    let ans = Number.NEGATIVE_INFINITY;
    let pre = 0;
    for (let i = 0; i < n - 1; ++i) {
        pre += nums[i];
        ans = Math.max(ans, pre - suf[i + 1]);
    }
    return ans;
}
