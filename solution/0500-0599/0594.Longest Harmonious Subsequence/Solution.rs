use std::collections::HashMap;

impl Solution {
    pub fn find_lhs(nums: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut ans = 0;
        for (&x, &c) in &cnt {
            if let Some(&y) = cnt.get(&(x + 1)) {
                ans = ans.max(c + y);
            }
        }
        ans
    }
}
