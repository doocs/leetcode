use std::cmp::{max, min};
use std::i32::{MAX, MIN};

impl Solution {
    pub fn max_difference(S: String, k: i32) -> i32 {
        let s: Vec<usize> = S
            .chars()
            .map(|c| c.to_digit(10).unwrap() as usize)
            .collect();
        let k = k as usize;
        let mut ans = MIN;

        for a in 0..5 {
            for b in 0..5 {
                if a == b {
                    continue;
                }

                let mut curA = 0;
                let mut curB = 0;
                let mut preA = 0;
                let mut preB = 0;
                let mut t = [[MAX; 2]; 2];
                let mut l: isize = -1;

                for (r, &x) in s.iter().enumerate() {
                    curA += (x == a) as i32;
                    curB += (x == b) as i32;

                    while (r as isize - l) as usize >= k && curB - preB >= 2 {
                        let i = (preA & 1) as usize;
                        let j = (preB & 1) as usize;
                        t[i][j] = min(t[i][j], preA - preB);
                        l += 1;
                        if l >= 0 {
                            preA += (s[l as usize] == a) as i32;
                            preB += (s[l as usize] == b) as i32;
                        }
                    }

                    let i = (curA & 1 ^ 1) as usize;
                    let j = (curB & 1) as usize;
                    if t[i][j] != MAX {
                        ans = max(ans, curA - curB - t[i][j]);
                    }
                }
            }
        }

        ans
    }
}
