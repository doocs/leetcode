function specialPerm(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const m = 1 << n;
    const f = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 1; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                const ii = i ^ (1 << j);
                if (ii === 0) {
                    f[i][j] = 1;
                    continue;
                }
                for (let k = 0; k < n; ++k) {
                    if (nums[j] % nums[k] === 0 || nums[k] % nums[j] === 0) {
                        f[i][j] = (f[i][j] + f[ii][k]) % mod;
                    }
                }
            }
        }
    }

    return f[m - 1].reduce((acc, x) => (acc + x) % mod);
}
