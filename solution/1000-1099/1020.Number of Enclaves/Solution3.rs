impl Solution {
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let dirs = [-1, 0, 1, 0, -1];

        let flood = |grid: &mut Vec<Vec<i32>>, i: usize, j: usize| {
            grid[i][j] = 0;
            let mut stk = vec![(i, j)];
            while let Some((a, b)) = stk.pop() {
                for k in 0..4 {
                    let x = a as i32 + dirs[k];
                    let y = b as i32 + dirs[k + 1];
                    if x >= 0 && y >= 0 {
                        let (x, y) = (x as usize, y as usize);
                        if x < m && y < n && grid[x][y] == 1 {
                            grid[x][y] = 0;
                            stk.push((x, y));
                        }
                    }
                }
            }
        };

        for j in 0..n {
            if grid[0][j] == 1 {
                flood(&mut grid, 0, j);
            }
            if grid[m - 1][j] == 1 {
                flood(&mut grid, m - 1, j);
            }
        }

        for i in 0..m {
            if grid[i][0] == 1 {
                flood(&mut grid, i, 0);
            }
            if grid[i][n - 1] == 1 {
                flood(&mut grid, i, n - 1);
            }
        }

        grid.into_iter().flatten().filter(|&x| x == 1).count() as i32
    }
}
