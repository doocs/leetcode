function minimumCost(nums: number[], k: number): number {
    const MOD = 1000000007n;
    let cnt = 0n;
    let cur = BigInt(k);
    const K = BigInt(k);

    for (const x of nums) {
        const diff = BigInt(x) - cur;
        if (diff > 0n) {
            const m = (diff + K - 1n) / K;
            cur += m * K;
            cnt += m;
        }
        cur -= BigInt(x);
    }

    cnt %= MOD;
    return Number((((cnt + 1n) * cnt) / 2n) % MOD);
}
