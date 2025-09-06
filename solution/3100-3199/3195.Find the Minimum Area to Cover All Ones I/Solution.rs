impl Solution {
    pub fn minimum_area(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut x1 = m as i32;
        let mut y1 = n as i32;
        let mut x2 = 0i32;
        let mut y2 = 0i32;

        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == 1 {
                    x1 = x1.min(i as i32);
                    y1 = y1.min(j as i32);
                    x2 = x2.max(i as i32);
                    y2 = y2.max(j as i32);
                }
            }
        }

        (x2 - x1 + 1) * (y2 - y1 + 1)
    }
}
