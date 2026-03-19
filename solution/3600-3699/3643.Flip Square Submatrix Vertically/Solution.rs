impl Solution {
    pub fn reverse_submatrix(
        mut grid: Vec<Vec<i32>>,
        x: i32,
        y: i32,
        k: i32,
    ) -> Vec<Vec<i32>> {
        let x = x as usize;
        let y = y as usize;
        let k = k as usize;

        for i in x..(x + k / 2) {
            let i2 = x + k - 1 - (i - x);
            for j in y..(y + k) {
                let t = grid[i][j];
                grid[i][j] = grid[i2][j];
                grid[i2][j] = t;
            }
        }

        grid
    }
}
