function sumOfBlocks(n: number): number {
    const mod = 1000000007;
    let k = 1;
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        let x = 1;
        for (let j = k; j < k + i; j++) {
            x = (x * j) % mod;
        }
        ans = (ans + x) % mod;
        k += i;
    }
    return ans;
}
