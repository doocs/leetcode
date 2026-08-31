function minOperations(nums: number[], sum: number): number {
    const inf = 1e9;
    const f = Array(sum + 1).fill(inf);
    f[0] = 0;

    for (const x of nums) {
        for (let w = sum; w >= 0; --w) {
            for (let i = 0, y = x; y <= w; ++i, y *= 2) {
                f[w] = Math.min(f[w], f[w - y] + i);
            }

            for (let i = 1, y = Math.floor(x / 2); y > 0; ++i, y = Math.floor(y / 2)) {
                for (let j = 0, z = y; z <= w; ++j, z *= 2) {
                    f[w] = Math.min(f[w], f[w - z] + i + j);
                }
            }
        }
    }

    return f[sum] === inf ? -1 : f[sum];
}
