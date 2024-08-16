function sumOfPower(nums: number[], k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(k + 1).fill(0);
    f[0] = 1;
    for (const x of nums) {
        for (let j = k; ~j; --j) {
            f[j] = (f[j] * 2) % mod;
            if (j >= x) {
                f[j] = (f[j] + f[j - x]) % mod;
            }
        }
    }
    return f[k];
}
