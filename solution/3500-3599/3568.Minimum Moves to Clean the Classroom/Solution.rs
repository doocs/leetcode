use std::collections::VecDeque;

impl Solution {
    pub fn min_moves(classroom: Vec<String>, energy: i32) -> i32 {
        let m = classroom.len();
        let n = classroom[0].len();
        let e = energy as usize;
        let mut d = vec![vec![0; n]; m];
        let mut x = 0;
        let mut y = 0;
        let mut cnt = 0;
        for i in 0..m {
            let row = classroom[i].as_bytes();
            for j in 0..n {
                if row[j] == b'S' {
                    x = i;
                    y = j;
                } else if row[j] == b'L' {
                    d[i][j] = cnt;
                    cnt += 1;
                }
            }
        }
        if cnt == 0 {
            return 0;
        }
        let masks = 1usize << cnt;
        let mut vis = vec![false; m * n * (e + 1) * masks];
        let id = |i: usize, j: usize, en: usize, mask: usize| {
            ((i * n + j) * (e + 1) + en) * masks + mask
        };
        let full = masks - 1;
        vis[id(x, y, e, full)] = true;
        let mut q = VecDeque::from([(x, y, e, full)]);
        let dirs = [-1, 0, 1, 0, -1];
        let mut ans = 0;
        while !q.is_empty() {
            for _ in 0..q.len() {
                let (i, j, cur, mask) = q.pop_front().unwrap();
                if mask == 0 {
                    return ans;
                }
                if cur == 0 {
                    continue;
                }
                for k in 0..4 {
                    let nx = i as i32 + dirs[k];
                    let ny = j as i32 + dirs[k + 1];
                    if nx < 0 || ny < 0 {
                        continue;
                    }
                    let nx = nx as usize;
                    let ny = ny as usize;
                    if nx >= m || ny >= n {
                        continue;
                    }
                    let c = classroom[nx].as_bytes()[ny];
                    if c == b'X' {
                        continue;
                    }
                    let nxt_e = if c == b'R' { e } else { cur - 1 };
                    let mut nxt_mask = mask;
                    if c == b'L' {
                        nxt_mask &= !(1 << d[nx][ny]);
                    }
                    let idx = id(nx, ny, nxt_e, nxt_mask);
                    if !vis[idx] {
                        vis[idx] = true;
                        q.push_back((nx, ny, nxt_e, nxt_mask));
                    }
                }
            }
            ans += 1;
        }
        -1
    }
}
