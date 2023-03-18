function cheapestJump(coins: number[], maxJump: number): number[] {
    const n = coins.length;
    const ans: number[] = [];
    if (coins[n - 1] == -1) {
        return ans;
    }
    const inf = 1 << 30;
    const f: number[] = new Array(n).fill(inf);
    f[n - 1] = coins[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        if (coins[i] !== -1) {
            for (let j = i + 1; j < Math.min(n, i + maxJump + 1); ++j) {
                f[i] = Math.min(f[i], f[j] + coins[i]);
            }
        }
    }
    if (f[0] === inf) {
        return ans;
    }
    for (let i = 0, s = f[0]; i < n; ++i) {
        if (f[i] == s) {
            s -= coins[i];
            ans.push(i + 1);
        }
    }
    return ans;
}
