function minMoves(nums: number[], limit: number): number {
    const n = nums.length;
    const d: number[] = Array(limit * 2 + 2).fill(0);
    for (let i = 0; i < n >> 1; ++i) {
        const x = Math.min(nums[i], nums[n - 1 - i]);
        const y = Math.max(nums[i], nums[n - 1 - i]);
        d[2] += 2;
        d[x + 1] -= 2;
        d[x + 1] += 1;
        d[x + y] -= 1;
        d[x + y + 1] += 1;
        d[y + limit + 1] -= 1;
        d[y + limit + 1] += 2;
    }
    let ans = n;
    let s = 0;
    for (let i = 2; i < d.length; ++i) {
        s += d[i];
        ans = Math.min(ans, s);
    }
    return ans;
}
