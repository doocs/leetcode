impl Solution {
    pub fn max_value(mut grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let m = grid[0].len();
        for i in 1..n {
            grid[i][0] += grid[i - 1][0];
        }
        for i in 1..m {
            grid[0][i] += grid[0][i - 1];
        }
        for i in 1..n {
            for j in 1..m {
                grid[i][j] += grid[i][j - 1].max(grid[i - 1][j]);
            }
        }
        grid[n - 1][m - 1]
    }
}
