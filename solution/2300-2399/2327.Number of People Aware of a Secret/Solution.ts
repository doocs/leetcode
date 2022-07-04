function peopleAwareOfSecret(n: number, delay: number, forget: number): number {
    let dp = new Array(n + 1).fill(0n);
    dp[1] = 1n;
    for (let i = 2; i <= n; i++) {
        let pre = 0n;
        for (let j = i - forget + 1; j <= i - delay; j++) {
            if (j > 0) {
                pre += dp[j];
            }
        }
        dp[i] = pre;
    }
    let pre = 0n;
    let i = n + 1;
    for (let j = i - forget; j < i; j++) {
        if (j > 0) {
            pre += dp[j];
        }
    }
    return Number(pre % BigInt(10 ** 9 + 7));
}
