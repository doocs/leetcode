function getLeastFrequentDigit(n: number): number {
    const cnt: number[] = Array(10).fill(0);
    for (; n; n = (n / 10) | 0) {
        cnt[n % 10]++;
    }
    let [ans, f] = [0, Number.MAX_SAFE_INTEGER];
    for (let x = 0; x < 10; ++x) {
        if (cnt[x] > 0 && cnt[x] < f) {
            f = cnt[x];
            ans = x;
        }
    }
    return ans;
}
