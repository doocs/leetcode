impl Solution {
    pub fn find_final_value(nums: Vec<i32>, original: i32) -> i32 {
        use std::collections::HashSet;
        let s: HashSet<i32> = nums.into_iter().collect();
        let mut original = original;
        while s.contains(&original) {
            original <<= 1;
        }
        original
    }
}
