impl Solution {
    pub fn number_of_ways(n: i32, x: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let n = n as usize;
        let x = x as u32;
        let mut f = vec![vec![0; n + 1]; n + 1];
        f[0][0] = 1;
        for i in 1..=n {
            let k = (i as i64).pow(x);
            for j in 0..=n {
                f[i][j] = f[i - 1][j];
                if j >= k as usize {
                    f[i][j] = (f[i][j] + f[i - 1][j - k as usize]) % MOD;
                }
            }
        }
        f[n][n] as i32
    }
}
