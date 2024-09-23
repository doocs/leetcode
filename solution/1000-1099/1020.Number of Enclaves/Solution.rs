impl Solution {
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let dirs = [-1, 0, 1, 0, -1];

        fn dfs(grid: &mut Vec<Vec<i32>>, i: usize, j: usize, dirs: &[i32; 5]) {
            grid[i][j] = 0;
            for k in 0..4 {
                let (x, y) = (i as i32 + dirs[k], j as i32 + dirs[k + 1]);
                if let Some(row) = grid.get_mut(x as usize) {
                    if let Some(&mut 1) = row.get_mut(y as usize) {
                        dfs(grid, x as usize, y as usize, dirs);
                    }
                }
            }
        }

        for j in 0..n {
            if grid[0][j] == 1 {
                dfs(&mut grid, 0, j, &dirs);
            }
            if grid[m - 1][j] == 1 {
                dfs(&mut grid, m - 1, j, &dirs);
            }
        }

        for i in 0..m {
            if grid[i][0] == 1 {
                dfs(&mut grid, i, 0, &dirs);
            }
            if grid[i][n - 1] == 1 {
                dfs(&mut grid, i, n - 1, &dirs);
            }
        }

        grid.into_iter().flatten().filter(|&x| x == 1).count() as i32
    }
}
