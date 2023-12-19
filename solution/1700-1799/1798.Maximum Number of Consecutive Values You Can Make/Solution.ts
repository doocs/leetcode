function getMaximumConsecutive(coins: number[]): number {
    coins.sort((a, b) => a - b);
    let ans = 1;
    for (const v of coins) {
        if (v > ans) {
            break;
        }
        ans += v;
    }
    return ans;
}
