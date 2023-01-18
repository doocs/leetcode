impl Solution {
    pub fn ones_minus_zeros(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let m = grid.len();
        let n = grid[0].len();
        let mut rows = vec![0; m];
        let mut cols = vec![0; n];
        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == 1 {
                    rows[i] += 1;
                    cols[j] += 1;
                }
            }
        }
        let mut ans = vec![vec![0; n]; m];
        for i in 0..m {
            for j in 0..n {
                ans[i][j] = (rows[i] + cols[j] - (m - rows[i]) - (n - cols[j])) as i32;
            }
        }
        ans
    }
}
