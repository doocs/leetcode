function sumDigitDifferences(nums: number[]): number {
    const n = nums.length;
    const m = Math.floor(Math.log10(nums[0])) + 1;
    let ans: bigint = BigInt(0);
    for (let k = 0; k < m; ++k) {
        const cnt: number[] = Array(10).fill(0);
        for (let i = 0; i < n; ++i) {
            ++cnt[nums[i] % 10];
            nums[i] = Math.floor(nums[i] / 10);
        }
        for (let i = 0; i < 10; ++i) {
            ans += BigInt(cnt[i]) * BigInt(n - cnt[i]);
        }
    }
    ans /= BigInt(2);
    return Number(ans);
}
