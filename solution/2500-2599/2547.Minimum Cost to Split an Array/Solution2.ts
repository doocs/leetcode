function minCost(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const cnt: number[] = Array(n).fill(0);
        let one = 0;
        let ans = Infinity;
        for (let j = i; j < n; ++j) {
            const x = ++cnt[nums[j]];
            if (x == 1) {
                ++one;
            } else if (x == 2) {
                --one;
            }
            ans = Math.min(ans, k + j - i + 1 - one + f[j + 1]);
        }
        f[i] = ans;
    }
    return f[0];
}
