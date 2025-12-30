use std::collections::VecDeque;

impl Solution {
    pub fn latest_day_to_cross(row: i32, col: i32, cells: Vec<Vec<i32>>) -> i32 {
        let mut l: i32 = 1;
        let mut r: i32 = cells.len() as i32;
        let m = row as usize;
        let n = col as usize;

        let check = |k: i32, cells: &Vec<Vec<i32>>| -> bool {
            let mut g = vec![vec![0i32; n]; m];
            for i in 0..k as usize {
                let x = (cells[i][0] - 1) as usize;
                let y = (cells[i][1] - 1) as usize;
                g[x][y] = 1;
            }
            let dirs = [-1, 0, 1, 0, -1];
            let mut q: VecDeque<(usize, usize)> = VecDeque::new();
            for j in 0..n {
                if g[0][j] == 0 {
                    q.push_back((0, j));
                    g[0][j] = 1;
                }
            }
            while let Some((x, y)) = q.pop_front() {
                if x == m - 1 {
                    return true;
                }
                for i in 0..4 {
                    let nx = x as i32 + dirs[i];
                    let ny = y as i32 + dirs[i + 1];
                    if nx >= 0
                        && nx < m as i32
                        && ny >= 0
                        && ny < n as i32
                        && g[nx as usize][ny as usize] == 0
                    {
                        q.push_back((nx as usize, ny as usize));
                        g[nx as usize][ny as usize] = 1;
                    }
                }
            }
            false
        };

        while l < r {
            let mid = (l + r + 1) >> 1;
            if check(mid, &cells) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        l
    }
}
