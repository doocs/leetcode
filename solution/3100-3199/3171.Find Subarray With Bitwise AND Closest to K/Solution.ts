function minimumDifference(nums: number[], k: number): number {
    const m = Math.max(...nums).toString(2).length;
    const n = nums.length;
    const cnt: number[] = Array(m).fill(0);
    let ans = Infinity;
    for (let i = 0, j = 0, s = -1; j < n; ++j) {
        s &= nums[j];
        ans = Math.min(ans, Math.abs(s - k));
        for (let h = 0; h < m; ++h) {
            if (((nums[j] >> h) & 1) ^ 1) {
                ++cnt[h];
            }
        }
        while (i < j && s < k) {
            for (let h = 0; h < m; ++h) {
                if (((nums[i] >> h) & 1) ^ 1 && --cnt[h] === 0) {
                    s |= 1 << h;
                }
            }
            ans = Math.min(ans, Math.abs(s - k));
            ++i;
        }
    }
    return ans;
}
