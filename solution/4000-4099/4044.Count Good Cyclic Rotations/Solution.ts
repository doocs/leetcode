function countGoodRotations(nums: number[]): number {
    const n = nums.length;
    const m = Math.floor(n / 2);

    let l = 0,
        r = 0;
    for (let i = 0; i < m; i++) {
        l += nums[i];
    }
    for (let i = m; i < n; i++) {
        r += nums[i];
    }

    let ans = l > r ? 1 : 0;

    for (let i = 0; i < n - 1; i++) {
        l -= nums[i];
        r += nums[i];
        l += nums[(i + m) % n];
        r -= nums[(i + m) % n];
        ans += l > r ? 1 : 0;
    }

    return ans;
}
