impl Solution {
    pub fn contains_cycle(grid: Vec<Vec<char>>) -> bool {
        let m = grid.len();
        let n = grid[0].len();
        let mut vis = vec![vec![false; n]; m];
        let dirs = vec![-1, 0, 1, 0, -1];

        fn dfs(
            x: usize,
            y: usize,
            px: isize,
            py: isize,
            grid: &Vec<Vec<char>>,
            vis: &mut Vec<Vec<bool>>,
            dirs: &Vec<isize>,
        ) -> bool {
            vis[x][y] = true;
            for k in 0..4 {
                let nx = (x as isize + dirs[k]) as usize;
                let ny = (y as isize + dirs[k + 1]) as usize;
                if nx < grid.len() && ny < grid[0].len() {
                    if grid[nx][ny] != grid[x][y] || (nx as isize == px && ny as isize == py) {
                        continue;
                    }
                    if vis[nx][ny] || dfs(nx, ny, x as isize, y as isize, grid, vis, dirs) {
                        return true;
                    }
                }
            }
            false
        }

        for i in 0..m {
            for j in 0..n {
                if !vis[i][j] && dfs(i, j, -1, -1, &grid, &mut vis, &dirs) {
                    return true;
                }
            }
        }
        false
    }
}
