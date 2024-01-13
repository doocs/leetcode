use std::collections::HashSet;

impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let mut ans = -1;
        let mut h = HashSet::new();

        for &n in &nums {
            h.insert(n);
        }

        for &n in &nums {
            if h.contains(&-n) && n > ans {
                ans = n;
            }
        }

        ans
    }
}
