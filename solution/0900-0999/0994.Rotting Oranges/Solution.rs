use std::collections::VecDeque;

impl Solution {
    pub fn oranges_rotting(mut grid: Vec<Vec<i32>>) -> i32 {
        let mut queue = VecDeque::new();
        let m = grid.len();
        let n = grid[0].len();
        // 新鲜橘子数量
        let mut count = 0;
        for i in 0..m {
            for j in 0..n {
                match grid[i][j] {
                    1 => count += 1,
                    2 => queue.push_back([i as i32, j as i32]),
                    _ => (),
                }
            }
        }
        let mut res = 0;
        let dirs = [1, 0, -1, 0, 1];
        while count != 0 && queue.len() != 0 {
            let mut len = queue.len();
            while len != 0 {
                let [x, y] = queue.pop_front().unwrap();
                for i in 0..4 {
                    let new_x = x + dirs[i];
                    let new_y = y + dirs[i + 1];
                    if new_x >= 0
                        && new_x < m as i32
                        && new_y >= 0
                        && new_y < n as i32
                        && grid[new_x as usize][new_y as usize] == 1
                    {
                        grid[new_x as usize][new_y as usize] = 2;
                        queue.push_back([new_x, new_y]);
                        count -= 1;
                    }
                }
                len -= 1;
            }
            res += 1;
        }
        if count != 0 {
            return -1;
        }
        res
    }
}
