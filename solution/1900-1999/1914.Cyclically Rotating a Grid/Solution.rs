impl Solution {
    pub fn rotate_grid(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let mut grid = grid;
        let m = grid.len();
        let n = grid[0].len();

        let mut rotate = |p: usize, mut k: usize| {
            let mut nums = Vec::new();
            for j in p..n - p - 1 {
                nums.push(grid[p][j]);
            }
            for i in p..m - p - 1 {
                nums.push(grid[i][n - p - 1]);
            }
            for j in (p + 1..n - p).rev() {
                nums.push(grid[m - p - 1][j]);
            }
            for i in (p + 1..m - p).rev() {
                nums.push(grid[i][p]);
            }
            let l = nums.len();
            if l == 0 {
                return;
            }
            k %= l;
            if k == 0 {
                return;
            }
            for j in p..n - p - 1 {
                grid[p][j] = nums[k];
                k = (k + 1) % l;
            }
            for i in p..m - p - 1 {
                grid[i][n - p - 1] = nums[k];
                k = (k + 1) % l;
            }
            for j in (p + 1..n - p).rev() {
                grid[m - p - 1][j] = nums[k];
                k = (k + 1) % l;
            }
            for i in (p + 1..m - p).rev() {
                grid[i][p] = nums[k];
                k = (k + 1) % l;
            }
        };

        let layers = std::cmp::min(m / 2, n / 2);
        for i in 0..layers {
            rotate(i, k as usize);
        }
        grid
    }
}
