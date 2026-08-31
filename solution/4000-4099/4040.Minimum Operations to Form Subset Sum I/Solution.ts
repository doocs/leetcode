function minOperations(nums: number[], sum: number): number {
    const inf = 1e9;
    const f = Array(sum + 1).fill(inf);
    f[0] = 0;

    for (const x of nums) {
        for (let w = sum; w >= 0; --w) {
            let i = 0;
            let y = x;

            while (y <= w) {
                f[w] = Math.min(f[w], f[w - y] + i);
                ++i;
                y *= 2;
            }

            i = 1;
            y = Math.floor(x / 2);

            while (y > 0) {
                if (y <= w) {
                    f[w] = Math.min(f[w], f[w - y] + i);
                }
                ++i;
                y = Math.floor(y / 2);
            }
        }
    }

    return f[sum] === inf ? -1 : f[sum];
}
