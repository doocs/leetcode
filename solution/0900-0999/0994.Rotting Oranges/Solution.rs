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
                    q.push_back(vec![i as i32, j as i32]);
                }
            }
        }

        let dirs: [i32; 5] = [-1, 0, 1, 0, -1];
        let mut ans = 0;

        while !q.is_empty() && cnt > 0 {
            let q_size = q.len();
            for _ in 0..q_size {
                let p = q.pop_front().unwrap();
                for d in 0..4 {
                    let x = p[0] + dirs[d];
                    let y = p[1] + dirs[d + 1];
                    if
                        x >= 0 &&
                        x < (m as i32) &&
                        y >= 0 &&
                        y < (n as i32) &&
                        grid[x as usize][y as usize] == 1
                    {
                        grid[x as usize][y as usize] = 2;
                        q.push_back(vec![x, y]);
                        cnt -= 1;
                    }
                }
            }
            ans += 1;
        }

        if cnt > 0 {
            return -1;
        }

        ans
    }
}
