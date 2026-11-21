function sumOfGoodIntegers(n: number, k: number): number {
    const MOD = 1000000007n;
    const N = BigInt(n),
        K = BigInt(k);
    let num: bigint;
    let memo: bigint[][][];

    const calc = (x: bigint, m: number): bigint => {
        num = x;
        memo = Array.from({ length: m }, () => Array.from({ length: 2 }, () => [-1n, -1n]));

        const dfs = (i: number, lim: boolean): [bigint, bigint] => {
            if (i < 0) {
                return [1n, 0n];
            }
            const j = lim ? 1 : 0;
            if (memo[i][j][0] !== -1n) {
                return memo[i][j] as [bigint, bigint];
            }
            const up = lim ? Number((num >> BigInt(i)) & 1n) : 1;
            let cnt = 0n,
                s = 0n;
            for (let cur = 0; cur <= up; cur++) {
                if (((N >> BigInt(i)) & 1n) === 1n && cur === 1) {
                    continue;
                }
                const [cnt1, s1] = dfs(i - 1, lim && cur === up);
                cnt = (cnt + cnt1) % MOD;
                s = (s + s1 + ((cnt1 * ((1n << BigInt(i)) % MOD)) % MOD) * BigInt(cur)) % MOD;
            }
            memo[i][j] = [cnt, s];
            return [cnt, s];
        };

        return dfs(m - 1, true)[1];
    };

    const lo = N - K > 1n ? N - K : 1n;
    const hi = N + K;
    const m = hi.toString(2).length;
    const right = calc(hi, m);
    const left = calc(lo - 1n, m);
    return Number((right - left + MOD) % MOD);
}
