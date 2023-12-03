function sumOfFlooredPairs(nums: number[]): number {
    const mx = Math.max(...nums);
    const cnt: number[] = Array(mx + 1).fill(0);
    const s: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let i = 1; i <= mx; ++i) {
        s[i] = s[i - 1] + cnt[i];
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (let y = 1; y <= mx; ++y) {
        if (cnt[y]) {
            for (let d = 1; d * y <= mx; ++d) {
                ans += cnt[y] * d * (s[Math.min((d + 1) * y - 1, mx)] - s[d * y - 1]);
                ans %= mod;
            }
        }
    }
    return ans;
}
