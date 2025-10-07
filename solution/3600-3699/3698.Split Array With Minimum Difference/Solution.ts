function splitArray(nums: number[]): number {
    const n = nums.length;
    const s: number[] = Array(n);
    const f: boolean[] = Array(n).fill(true);
    const g: boolean[] = Array(n).fill(true);

    s[0] = nums[0];
    for (let i = 1; i < n; ++i) {
        s[i] = s[i - 1] + nums[i];
        f[i] = f[i - 1];
        if (nums[i] <= nums[i - 1]) {
            f[i] = false;
        }
    }

    for (let i = n - 2; i >= 0; --i) {
        g[i] = g[i + 1];
        if (nums[i] <= nums[i + 1]) {
            g[i] = false;
        }
    }

    const INF = Number.MAX_SAFE_INTEGER;
    let ans = INF;

    for (let i = 0; i < n - 1; ++i) {
        if (f[i] && g[i + 1]) {
            const s1 = s[i];
            const s2 = s[n - 1] - s[i];
            ans = Math.min(ans, Math.abs(s1 - s2));
        }
    }

    return ans < INF ? ans : -1;
}
