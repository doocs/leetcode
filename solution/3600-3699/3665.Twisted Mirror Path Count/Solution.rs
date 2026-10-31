impl Solution {
    pub fn unique_paths(grid: Vec<Vec<i32>>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let n = grid[0].len();
        let mut f = vec![(0i32, 0i32); n + 1];
        f[1] = (1, 1);
        for row in &grid {
            for (j, &x) in row.iter().enumerate() {
                let (r, d) = (f[j].0, f[j + 1].1);
                f[j + 1] = if x == 1 {
                    (d, r)
                } else {
                    let s = (r + d) % MOD;
                    (s, s)
                };
            }
        }
        f[n].0
    }
}
