function minOperations(n: number): number {
    let ans = 0;
    for (let i = 0; i < n >> 1; ++i) {
        ans += n - ((i << 1) | 1);
    }
    return ans;
}
