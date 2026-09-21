function resultArray(nums: number[], k: number): number[] {
    const ans = Array(k).fill(0);
    let f = Array(k).fill(0);
    for (const x of nums) {
        const g = Array(k).fill(0);
        for (let r = 0; r < k; ++r) {
            g[(r * x) % k] += f[r];
        }
        g[x % k] += 1;
        for (let r = 0; r < k; ++r) {
            ans[r] += g[r];
        }
        f = g;
    }
    return ans;
}
