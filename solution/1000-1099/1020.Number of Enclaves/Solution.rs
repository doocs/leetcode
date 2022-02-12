impl Solution {
    fn dfs(grid: &mut Vec<Vec<i32>>, y: usize, x: usize) {
        if y >= grid.len() || x >= grid[0].len() || grid[y][x] == 0 {
            return;
        }
        grid[y][x] = 0;
        Solution::dfs(grid, y + 1, x);
        Solution::dfs(grid, y, x + 1);
        if y != 0 {
            Solution::dfs(grid, y - 1, x);
        }
        if x != 0 {
            Solution::dfs(grid, y, x - 1);
        }
    }
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let mut res = 0;
        let m = grid.len();
        let n = grid[0].len();
        for i in 0..m {
            Solution::dfs(&mut grid, i, 0);
            Solution::dfs(&mut grid, i, n - 1);
        }
        for i in 0..n {
            Solution::dfs(&mut grid, 0, i);
            Solution::dfs(&mut grid, m - 1, i);
        }
        for i in 1..m - 1 {
            for j in 1..n - 1 {
                if grid[i][j] == 1 {
                    res += 1;
                }
            }
        }
        res
    }
}
