function maxSubarraySum(nums: number[], k: number): number {
    const n = nums.length;
    const inf = -1e18;

    const f: number[][] = Array.from({ length: n + 1 }, () => {
        const arr = new Array(4).fill(inf);
        return arr;
    });

    f[0][0] = 0;
    let ans = inf;

    for (let i = 1; i <= n; i++) {
        const x = nums[i - 1];

        f[i][0] = Math.max(f[i - 1][0], 0) + x;
        f[i][1] = Math.max(Math.max(f[i - 1][0], f[i - 1][1]), 0) + x * k;
        f[i][2] = Math.max(Math.max(f[i - 1][0], f[i - 1][2]), 0) + Math.trunc(x / k);
        f[i][3] = Math.max(Math.max(f[i - 1][1], f[i - 1][2]), f[i - 1][3]) + x;

        ans = Math.max(ans, Math.max(...f[i]));
    }

    return ans;
}
