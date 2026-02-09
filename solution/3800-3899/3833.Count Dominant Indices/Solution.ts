function dominantIndices(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    let suf = nums[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] * (n - i - 1) > suf) {
            ans++;
        }
        suf += nums[i];
    }
    return ans;
}
