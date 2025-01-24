function minimumMoney(transactions: number[][]): number {
    const s = transactions.reduce((acc, [a, b]) => acc + Math.max(0, a - b), 0);
    let ans = 0;
    for (const [a, b] of transactions) {
        if (a > b) {
            ans = Math.max(ans, s + b);
        } else {
            ans = Math.max(ans, s + a);
        }
    }
    return ans;
}
