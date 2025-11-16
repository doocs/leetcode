function numSub(s: string): number {
    const mod = 1_000_000_007;
    let [ans, cur] = [0, 0];
    for (const c of s) {
        if (c === '0') {
            cur = 0;
        } else {
            cur++;
            ans = (ans + cur) % mod;
        }
    }
    return ans;
}
