function minimumTime(power: number[]): number {
    const n = power.length;
    const dp = new Array(1 << n).fill(Infinity);
    dp[0] = 0;
    for (let mask = 1; mask < 1 << n; ++mask) {
        const cnt = bitCount(mask);
        for (let i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                dp[mask] = Math.min(dp[mask], dp[mask ^ (1 << i)] + Math.ceil(power[i] / cnt));
            }
        }
    }
    return dp[dp.length - 1];
}

function bitCount(x) {
    let cnt = 0;
    for (let i = 0; i < 32; ++i) {
        if ((x >> i) & 1) {
            ++cnt;
        }
    }
    return cnt;
}
