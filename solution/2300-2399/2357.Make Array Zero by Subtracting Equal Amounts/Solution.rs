use std::collections::HashSet;
impl Solution {
    pub fn minimum_operations(nums: Vec<i32>) -> i32 {
        let mut s = nums.iter().collect::<HashSet<&i32>>();
        s.remove(&0);
        s.len() as i32
    }
}
