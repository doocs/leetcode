function peopleAwareOfSecret(n: number, delay: number, forget: number): number {
    const mod = 1e9 + 7;
    const m = (n << 1) + 10;
    const d: number[] = Array(m).fill(0);
    const cnt: number[] = Array(m).fill(0);

    cnt[1] = 1;
    for (let i = 1; i <= n; ++i) {
        if (cnt[i] > 0) {
            d[i] = (d[i] + cnt[i]) % mod;
            d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
            let nxt = i + delay;
            while (nxt < i + forget) {
                cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                ++nxt;
            }
        }
    }

    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        ans = (ans + d[i]) % mod;
    }
    return ans;
}
