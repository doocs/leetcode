impl Solution {
    pub fn count_negatives(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut i: i32 = m as i32 - 1;
        let mut j: usize = 0;
        let mut ans: i32 = 0;
        while i >= 0 && j < n {
            if grid[i as usize][j] >= 0 {
                j += 1;
            } else {
                ans += (n - j) as i32;
                i -= 1;
            }
        }
        ans
    }
}
