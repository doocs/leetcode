function trailingZeroes(n: number): number {
    let ans = 0;
    while (n) {
        n = Math.floor(n / 5);
        ans += n;
    }
    return ans;
}
