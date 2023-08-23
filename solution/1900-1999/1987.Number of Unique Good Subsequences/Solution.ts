function numberOfUniqueGoodSubsequences(binary: string): number {
    let [f, g] = [0, 0];
    let ans = 0;
    const mod = 1e9 + 7;
    for (const c of binary) {
        if (c === '0') {
            g = (g + f) % mod;
            ans = 1;
        } else {
            f = (f + g + 1) % mod;
        }
    }
    ans = (ans + g + f) % mod;
    return ans;
}
