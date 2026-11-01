impl Solution {
    pub fn unique_paths(grid: Vec<Vec<i32>>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let m = grid.len();
        let n = grid[0].len();
        let mut f = vec![vec![(0i32, 0i32); n]; m];
        for i in 0..m {
            for j in 0..n {
                let r = if j > 0 { f[i][j - 1].0 } else { 0 };
                let mut d = if i > 0 { f[i - 1][j].1 } else { 0 };
                if i == 0 && j == 0 {
                    d = 1;
                }
                f[i][j] = if grid[i][j] == 1 {
                    (d, r)
                } else {
                    let s = (r + d) % MOD;
                    (s, s)
                };
            }
        }
        f[m - 1][n - 1].0
    }
}
