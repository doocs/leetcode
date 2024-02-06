function countSubMultisets(nums: number[], l: number, r: number): number {
    const cnt: number[] = Array(20001).fill(0);
    const memo: number[] = Array(20001).fill(0);
    const mod: number = 1000000007;
    for (const n of nums) {
        cnt[n]++;
    }
    memo.fill(1, 0, cnt[1] + 1);
    let total: number = cnt[1];
    for (let n = 2; n <= r; ++n) {
        if (!cnt[n]) {
            continue;
        }
        const top: number = (cnt[n] + 1) * n;
        total += n * cnt[n];
        for (let i = n, ii = Math.min(total, r); i <= ii; ++i) {
            memo[i] = (memo[i] + memo[i - n]) % mod;
        }
        for (let i = Math.min(total, r); i >= top; --i) {
            memo[i] = (mod + memo[i] - memo[i - top]) % mod;
        }
    }
    let result: number = 0;
    for (let i = l; i <= r; i++) {
        result = (result + memo[i]) % mod;
    }
    return (result * (cnt[0] + 1)) % mod;
}
