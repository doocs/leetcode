impl Solution {
    pub fn count_negatives(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut i = m;
        let mut j = 0;
        let mut res = 0;
        while i > 0 && j < n {
            if grid[i - 1][j] >= 0 {
                j += 1;
            } else {
                res += n - j;
                i -= 1;
            }
        }
        res as i32
    }
}
