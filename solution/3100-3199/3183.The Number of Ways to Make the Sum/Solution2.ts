const m: number = 10 ** 5 + 1;
const mod: number = 10 ** 9 + 7;
const f: number[] = Array(m).fill(0);

(() => {
    f[0] = 1;
    const coins: number[] = [1, 2, 6];
    for (const x of coins) {
        for (let j = x; j < m; ++j) {
            f[j] = (f[j] + f[j - x]) % mod;
        }
    }
})();

function numberOfWays(n: number): number {
    let ans = f[n];
    if (n >= 4) {
        ans = (ans + f[n - 4]) % mod;
    }
    if (n >= 8) {
        ans = (ans + f[n - 8]) % mod;
    }
    return ans;
}
