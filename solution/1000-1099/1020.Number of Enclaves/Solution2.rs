use std::collections::VecDeque;

impl Solution {
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut q = VecDeque::new();
        let dirs = [-1, 0, 1, 0, -1];

        for j in 0..n {
            for &i in &[0, m - 1] {
                if grid[i][j] == 1 {
                    q.push_back((i, j));
                    grid[i][j] = 0;
                }
            }
        }

        for i in 0..m {
            for &j in &[0, n - 1] {
                if grid[i][j] == 1 {
                    q.push_back((i, j));
                    grid[i][j] = 0;
                }
            }
        }

        while let Some((i, j)) = q.pop_front() {
            for k in 0..4 {
                let x = i as isize + dirs[k];
                let y = j as isize + dirs[k + 1];
                if x >= 0 && x < m as isize && y >= 0 && y < n as isize {
                    let (x, y) = (x as usize, y as usize);
                    if grid[x][y] == 1 {
                        q.push_back((x, y));
                        grid[x][y] = 0;
                    }
                }
            }
        }

        grid.into_iter().flatten().sum()
    }
}
