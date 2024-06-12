function maxTotalReward(rewardValues: number[]): number {
    const nums = Array.from(new Set(rewardValues)).sort((a, b) => a - b);
    const n = nums.length;
    const m = nums[n - 1] << 1;
    const f: boolean[] = Array(m).fill(false);
    f[0] = true;
    for (const v of nums) {
        for (let j = 1; j < m; ++j) {
            if (0 <= j - v && j - v < v) {
                f[j] = f[j] || f[j - v];
            }
        }
    }
    let ans = m - 1;
    while (!f[ans]) {
        --ans;
    }
    return ans;
}
