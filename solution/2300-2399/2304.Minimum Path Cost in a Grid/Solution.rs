impl Solution {
    pub fn min_path_cost(grid: Vec<Vec<i32>>, move_cost: Vec<Vec<i32>>) -> i32 {
        let (m, n) = (grid.len(), grid[0].len());
        let mut dp = vec![0; n];
        for i in 0..m - 1 {
            let mut counter = vec![i32::MAX; n];
            for j in 0..n {
                let val = grid[i][j];
                for k in 0..n {
                    counter[k] = counter[k].min(val + move_cost[val as usize][k] + dp[j]);
                }
            }
            for j in 0..n {
                dp[j] = counter[j];
            }
        }
        let mut res = i32::MAX;
        for i in 0..n {
            res = res.min(dp[i] + grid[m - 1][i]);
        }
        res
    }
}
