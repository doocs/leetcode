function squareFreeSubsets(nums: number[]): number {
    const primes: number[] = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29];
    const cnt: number[] = Array(31).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    const mod: number = Math.pow(10, 9) + 7;
    const n: number = primes.length;
    const f: number[] = Array(1 << n).fill(0);
    f[0] = 1;
    for (let i = 0; i < cnt[1]; ++i) {
        f[0] = (f[0] * 2) % mod;
    }
    for (let x = 2; x < 31; ++x) {
        if (cnt[x] === 0 || x % 4 === 0 || x % 9 === 0 || x % 25 === 0) {
            continue;
        }
        let mask: number = 0;
        for (let i = 0; i < n; ++i) {
            if (x % primes[i] === 0) {
                mask |= 1 << i;
            }
        }
        for (let state = (1 << n) - 1; state > 0; --state) {
            if ((state & mask) === mask) {
                f[state] = (f[state] + cnt[x] * f[state ^ mask]) % mod;
            }
        }
    }
    let ans: number = 0;
    for (let i = 0; i < 1 << n; ++i) {
        ans = (ans + f[i]) % mod;
    }
    ans -= 1;
    return ans >= 0 ? ans : ans + mod;
}
