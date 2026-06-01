function digitFrequencyScore(n: number): number {
    let ans = 0;
    for (; n; n = Math.floor(n / 10)) {
        ans += n % 10;
    }
    return ans;
}
