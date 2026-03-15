function countCommas(n: number): number {
    let ans = 0;
    for (let x = 1000; x <= n; x *= 1000) {
        ans += n - x + 1;
    }
    return ans;
}
