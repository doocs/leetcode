impl Solution {
    pub fn number_of_paths(grid: Vec<Vec<i32>>, K: i32) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let m = grid.len();
        let n = grid[0].len();
        let K = K as usize;
        let mut f = vec![vec![vec![0; K]; n]; m];
        f[0][0][grid[0][0] as usize % K] = 1;
        for i in 0..m {
            for j in 0..n {
                for k in 0..K {
                    let k0 = ((k + K - grid[i][j] as usize % K) % K) as usize;
                    if i > 0 {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k0]) % MOD;
                    }
                    if j > 0 {
                        f[i][j][k] = (f[i][j][k] + f[i][j - 1][k0]) % MOD;
                    }
                }
            }
        }
        f[m - 1][n - 1][0]
    }
}
