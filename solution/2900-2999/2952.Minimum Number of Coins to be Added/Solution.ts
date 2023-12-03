function minimumAddedCoins(coins: number[], target: number): number {
    coins.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, s = 1; s <= target; ) {
        if (i < coins.length && coins[i] <= s) {
            s += coins[i++];
        } else {
            s <<= 1;
            ++ans;
        }
    }
    return ans;
}
