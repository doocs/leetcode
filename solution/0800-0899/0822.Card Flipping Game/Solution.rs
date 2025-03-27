use std::collections::HashSet;

impl Solution {
    pub fn flipgame(fronts: Vec<i32>, backs: Vec<i32>) -> i32 {
        let n = fronts.len();
        let mut s: HashSet<i32> = HashSet::new();

        for i in 0..n {
            if fronts[i] == backs[i] {
                s.insert(fronts[i]);
            }
        }

        let mut ans = 9999;
        for &v in fronts.iter() {
            if !s.contains(&v) {
                ans = ans.min(v);
            }
        }
        for &v in backs.iter() {
            if !s.contains(&v) {
                ans = ans.min(v);
            }
        }

        if ans == 9999 {
            0
        } else {
            ans
        }
    }
}
