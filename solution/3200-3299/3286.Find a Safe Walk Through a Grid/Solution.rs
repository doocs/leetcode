impl Solution {
    pub fn find_safe_walk(grid: Vec<Vec<i32>>, health: i32) -> bool {
        let m = grid.len();
        let n = grid[0].len();
        let inf: i32 = i32::MAX / 2;

        let mut dist = vec![vec![inf; n]; m];
        dist[0][0] = grid[0][0];

        let mut q = std::collections::VecDeque::new();
        q.push_back((0usize, 0usize));

        let dirs = [(-1i32, 0i32), (1, 0), (0, -1), (0, 1)];

        while let Some((x, y)) = q.pop_front() {
            for (dx, dy) in dirs {
                let nx = x as i32 + dx;
                let ny = y as i32 + dy;

                if nx >= 0 && nx < m as i32 && ny >= 0 && ny < n as i32 {
                    let nx = nx as usize;
                    let ny = ny as usize;

                    let new_cost = dist[x][y] + grid[nx][ny];
                    if new_cost < dist[nx][ny] {
                        dist[nx][ny] = new_cost;
                        q.push_back((nx, ny));
                    }
                }
            }
        }

        dist[m - 1][n - 1] < health
    }
}
