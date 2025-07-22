use std::collections::HashSet;

impl Solution {
    pub fn minimum_operations(nums: Vec<i32>) -> i32 {
        let mut s = HashSet::new();
        for i in (0..nums.len()).rev() {
            if !s.insert(nums[i]) {
                return (i / 3) as i32 + 1;
            }
        }
        0
    }
}
