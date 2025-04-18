impl Solution {
    pub fn min_operations(nums: Vec<i32>, k: i32) -> i32 {
        use std::collections::HashSet;

        let mut s = HashSet::new();
        let mut mi = i32::MAX;

        for &x in &nums {
            if x < k {
                return -1;
            }
            s.insert(x);
            mi = mi.min(x);
        }

        (s.len() as i32) - if mi == k { 1 } else { 0 }
    }
}
