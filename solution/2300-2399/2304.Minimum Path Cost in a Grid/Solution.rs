impl Solution {
    pub fn min_path_cost(grid: Vec<Vec<i32>>, move_cost: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut f = grid[0].clone();

        for i in 1..m {
            let mut g: Vec<i32> = vec![i32::MAX; n];
            for j in 0..n {
                for k in 0..n {
                    g[j] = g[j].min(f[k] + move_cost[grid[i - 1][k] as usize][j] + grid[i][j]);
                }
            }
            f.copy_from_slice(&g);
        }

        f.iter().cloned().min().unwrap_or(0)
    }
}
