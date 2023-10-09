function maxSum(nums: number[], k: number): number {
    const cnt: number[] = Array(31).fill(0);
    for (const x of nums) {
        for (let i = 0; i < 31; ++i) {
            if ((x >> i) & 1) {
                ++cnt[i];
            }
        }
    }
    let ans = 0n;
    const mod = 1e9 + 7;
    while (k-- > 0) {
        let x = 0;
        for (let i = 0; i < 31; ++i) {
            if (cnt[i] > 0) {
                x |= 1 << i;
                --cnt[i];
            }
        }
        ans = (ans + BigInt(x) * BigInt(x)) % BigInt(mod);
    }
    return Number(ans);
}
