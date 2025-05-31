use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn snakes_and_ladders(board: Vec<Vec<i32>>) -> i32 {
        let n = board.len();
        let m = (n * n) as i32;
        let mut q = VecDeque::new();
        q.push_back(1);
        let mut vis = HashSet::new();
        vis.insert(1);
        let mut ans = 0;

        while !q.is_empty() {
            for _ in 0..q.len() {
                let x = q.pop_front().unwrap();
                if x == m {
                    return ans;
                }
                for y in x + 1..=i32::min(x + 6, m) {
                    let (mut i, mut j) = ((y - 1) / n as i32, (y - 1) % n as i32);
                    if i % 2 == 1 {
                        j = (n as i32 - 1) - j;
                    }
                    i = (n as i32 - 1) - i;
                    let z = if board[i as usize][j as usize] == -1 {
                        y
                    } else {
                        board[i as usize][j as usize]
                    };
                    if !vis.contains(&z) {
                        vis.insert(z);
                        q.push_back(z);
                    }
                }
            }
            ans += 1;
        }

        -1
    }
}
