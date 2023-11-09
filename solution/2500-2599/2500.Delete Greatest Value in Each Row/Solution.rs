impl Solution {
    pub fn delete_greatest_value(grid: Vec<Vec<i32>>) -> i32 {
        let mut grid = grid;
        for i in 0..grid.len() {
            grid[i].sort();
        }

        let mut ans = 0;
        for j in 0..grid[0].len() {
            let mut mx = 0;

            for i in 0..grid.len() {
                if grid[i][j] > mx {
                    mx = grid[i][j];
                }
            }

            ans += mx;
        }

        ans
    }
}
