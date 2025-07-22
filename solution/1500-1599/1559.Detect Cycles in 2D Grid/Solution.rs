impl Solution {
    pub fn contains_cycle(grid: Vec<Vec<char>>) -> bool {
        let m = grid.len();
        let n = grid[0].len();
        let mut vis = vec![vec![false; n]; m];
        let dirs = vec![-1, 0, 1, 0, -1];

        for i in 0..m {
            for j in 0..n {
                if !vis[i][j] {
                    let mut q = vec![(i as isize, j as isize, -1, -1)];
                    vis[i][j] = true;

                    while !q.is_empty() {
                        let (x, y, px, py) = q.pop().unwrap();

                        for k in 0..4 {
                            let nx = x + dirs[k];
                            let ny = y + dirs[k + 1];
                            if nx >= 0 && nx < m as isize && ny >= 0 && ny < n as isize {
                                let nx = nx as usize;
                                let ny = ny as usize;
                                if grid[nx][ny] != grid[x as usize][y as usize]
                                    || (nx == px as usize && ny == py as usize)
                                {
                                    continue;
                                }
                                if vis[nx][ny] {
                                    return true;
                                }
                                q.push((nx as isize, ny as isize, x, y));
                                vis[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        false
    }
}
