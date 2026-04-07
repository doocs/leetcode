use std::collections::HashSet;

impl Solution {
    pub fn robot_sim(commands: Vec<i32>, obstacles: Vec<Vec<i32>>) -> i32 {
        let dirs: [i32; 5] = [0, 1, 0, -1, 0];
        let mut s: HashSet<(i32, i32)> = HashSet::new();

        for o in obstacles {
            s.insert((o[0], o[1]));
        }

        let mut ans: i32 = 0;
        let mut k: i32 = 0;
        let mut x: i32 = 0;
        let mut y: i32 = 0;

        for c in commands {
            if c == -2 {
                k = (k + 3) % 4;
            } else if c == -1 {
                k = (k + 1) % 4;
            } else {
                for _ in 0..c {
                    let nx: i32 = x + dirs[k as usize];
                    let ny: i32 = y + dirs[k as usize + 1];

                    if s.contains(&(nx, ny)) {
                        break;
                    }

                    x = nx;
                    y = ny;
                    ans = ans.max(x * x + y * y);
                }
            }
        }

        ans
    }
}
