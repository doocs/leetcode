use std::collections::HashSet;

impl Solution {
    pub fn max_sum(nums: Vec<i32>) -> i32 {
        let mx = *nums.iter().max().unwrap_or(&0);
        if mx <= 0 {
            return mx;
        }

        let mut s = HashSet::new();
        let mut ans = 0;

        for &x in &nums {
            if x < 0 || s.contains(&x) {
                continue;
            }
            ans += x;
            s.insert(x);
        }

        ans
    }
}
