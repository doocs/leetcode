impl Solution {
    pub fn projection_area(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut res = 0;
        let mut x_max = vec![0; n];
        let mut y_max = vec![0; n];
        for i in 0..n {
            for j in 0..n {
                let val = grid[i][j];
                if val == 0 {
                    continue;
                }
                res += 1;
                x_max[i] = x_max[i].max(val);
                y_max[j] = y_max[j].max(val);
            }
        }
        res + y_max.iter().sum::<i32>() + x_max.iter().sum::<i32>()
    }
}
