function subsequencePairCount(nums: number[]): number {
    const mod = 1_000_000_007;
    const n = nums.length;
    const m = Math.max(...nums);
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: m + 1 }, () => new Array(m + 1).fill(-1)),
    );
    const gcd = (a: number, b: number): number => {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b !== 0) {
            [a, b] = [b, a % b];
        }
        return a;
    };
    const dfs = (i: number, j: number, k: number): number => {
        if (i < 0) {
            return j === k ? 1 : 0;
        }
        let res = f[i][j][k];
        if (res < 0) {
            const x = nums[i];
            res =
                (((dfs(i - 1, j, k) + dfs(i - 1, gcd(x, j), k)) % mod) + dfs(i - 1, j, gcd(x, k))) %
                mod;
            f[i][j][k] = res;
        }
        return res;
    };
    return (((dfs(n - 1, 0, 0) - 1) % mod) + mod) % mod;
}
