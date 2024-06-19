function consecutiveNumbersSum(n: number): number {
    let ans = 0;
    n <<= 1;
    for (let k = 1; k * (k + 1) <= n; ++k) {
        if (n % k === 0 && (Math.floor(n / k) + 1 - k) % 2 === 0) {
            ++ans;
        }
    }
    return ans;
}
