function maximumProduct(nums: number[], m: number): number {
    let ans = Number.MIN_SAFE_INTEGER;
    let mx = Number.MIN_SAFE_INTEGER;
    let mi = Number.MAX_SAFE_INTEGER;

    for (let i = m - 1; i < nums.length; i++) {
        const x = nums[i];
        const y = nums[i - m + 1];
        mi = Math.min(mi, y);
        mx = Math.max(mx, y);
        ans = Math.max(ans, x * mi, x * mx);
    }

    return ans;
}
