function minOperations(k: number): number {
    let ans = k;
    for (let a = 0; a < k; ++a) {
        const x = a + 1;
        const b = Math.ceil(k / x) - 1;
        ans = Math.min(ans, a + b);
    }
    return ans;
}
