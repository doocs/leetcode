use std::collections::HashSet;

impl Solution {
    pub fn maximum_removals(s: String, p: String, removable: Vec<i32>) -> i32 {
        let m = s.len();
        let n = p.len();
        let s = s.as_bytes();
        let p = p.as_bytes();

        let check = |k| {
            let mut i = 0;
            let mut j = 0;
            let ids: HashSet<i32> = removable[..k].iter().cloned().collect();
            while i < m && j < n {
                if !ids.contains(&(i as i32)) && s[i] == p[j] {
                    j += 1;
                }
                i += 1;
            }
            j == n
        };

        let mut left = 0;
        let mut right = removable.len();
        while left + 1 < right {
            let mid = left + (right - left) / 2;
            if check(mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if check(right) {
            return right as i32;
        }
        left as i32
    }
}
