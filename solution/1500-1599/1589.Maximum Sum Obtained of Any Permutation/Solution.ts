function maxSumRangeQuery(nums: number[], requests: number[][]): number {
    const n = nums.length;
    const d = new Array(n).fill(0);
    for (const [l, r] of requests) {
        d[l]++;
        if (r + 1 < n) {
            d[r + 1]--;
        }
    }
    for (let i = 1; i < n; ++i) {
        d[i] += d[i - 1];
    }
    nums.sort((a, b) => a - b);
    d.sort((a, b) => a - b);
    let ans = 0;
    const mod = 10 ** 9 + 7;
    for (let i = 0; i < n; ++i) {
        ans = (ans + nums[i] * d[i]) % mod;
    }
    return ans;
}
