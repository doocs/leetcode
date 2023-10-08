function differenceOfSums(n: number, m: number): number {
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        ans += i % m ? i : -i;
    }
    return ans;
}
