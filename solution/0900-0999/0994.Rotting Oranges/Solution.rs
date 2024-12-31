use std::collections::VecDeque;

impl Solution {
    pub fn oranges_rotting(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut q = VecDeque::new();
        let mut cnt = 0;
        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == 1 {
                    cnt += 1;
                } else if grid[i][j] == 2 {
                    q.push_back((i, j));
                }
            }
        }

        let dirs = [-1, 0, 1, 0, -1];
        for ans in 1.. {
            if q.is_empty() || cnt == 0 {
                break;
            }
            let mut size = q.len();
            for _ in 0..size {
                let (x, y) = q.pop_front().unwrap();
                for d in 0..4 {
                    let nx = x as isize + dirs[d] as isize;
                    let ny = y as isize + dirs[d + 1] as isize;
                    if nx >= 0 && nx < m as isize && ny >= 0 && ny < n as isize {
                        let nx = nx as usize;
                        let ny = ny as usize;
                        if grid[nx][ny] == 1 {
                            grid[nx][ny] = 2;
                            q.push_back((nx, ny));
                            cnt -= 1;
                            if cnt == 0 {
                                return ans;
                            }
                        }
                    }
                }
            }
        }
        if cnt > 0 {
            -1
        } else {
            0
        }
    }
}
