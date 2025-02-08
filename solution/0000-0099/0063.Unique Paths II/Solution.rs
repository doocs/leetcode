impl Solution {
    pub fn unique_paths_with_obstacles(obstacle_grid: Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        let mut f = vec![vec![-1; n]; m];
        Self::dfs(0, 0, &obstacle_grid, &mut f)
    }

    fn dfs(i: usize, j: usize, obstacle_grid: &Vec<Vec<i32>>, f: &mut Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        if i >= m || j >= n || obstacle_grid[i][j] == 1 {
            return 0;
        }
        if i == m - 1 && j == n - 1 {
            return 1;
        }
        if f[i][j] != -1 {
            return f[i][j];
        }
        let down = Self::dfs(i + 1, j, obstacle_grid, f);
        let right = Self::dfs(i, j + 1, obstacle_grid, f);
        f[i][j] = down + right;
        f[i][j]
    }
}
