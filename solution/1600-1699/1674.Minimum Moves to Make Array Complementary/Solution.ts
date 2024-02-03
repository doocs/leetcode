function minMoves(nums: number[], limit: number): number {
    const n = nums.length;
    const d: number[] = Array(limit * 2 + 2).fill(0);
    for (let i = 0; i < n >> 1; ++i) {
        const a = Math.min(nums[i], nums[n - i - 1]);
        const b = Math.max(nums[i], nums[n - i - 1]);

        d[2] += 2;
        d[limit * 2 + 1] -= 2;

        d[a + 1] -= 1;
        d[b + limit + 1] += 1;

        d[a + b] -= 1;
        d[a + b + 1] += 1;
    }
    let ans = n;
    let s = 0;
    for (let i = 2; i <= limit * 2; ++i) {
        s += d[i];
        if (ans > s) {
            ans = s;
        }
    }
    return ans;
}
