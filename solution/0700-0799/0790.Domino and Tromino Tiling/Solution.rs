impl Solution {
    pub fn num_tilings(n: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let mut f: [i64; 4] = [1, 0, 0, 0];
        for _ in 1..=n {
            let mut g = [0i64; 4];
            g[0] = (f[0] + f[1] + f[2] + f[3]) % MOD;
            g[1] = (f[2] + f[3]) % MOD;
            g[2] = (f[1] + f[3]) % MOD;
            g[3] = f[0] % MOD;
            f = g;
        }
        f[0] as i32
    }
}
