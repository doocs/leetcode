impl Solution {
    pub fn number_of_sets(n: i32, k: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let n = n as usize;
        let k = k as usize;
        let mut f = vec![vec![0i64; k + 1]; n + 1];
        let mut g = vec![vec![0i64; k + 1]; n + 1];
        f[1][0] = 1;
        for i in 2..=n {
            for j in 0..=k {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % MOD;
                g[i][j] = g[i - 1][j];
                if j > 0 {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % MOD;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % MOD;
                }
            }
        }
        ((f[n][k] + g[n][k]) % MOD) as i32
    }
}
