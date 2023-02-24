use std::collections::HashSet;
impl Solution {
    pub fn minimum_operations(nums: Vec<i32>) -> i32 {
        let mut set = nums.iter().collect::<HashSet<&i32>>();
        set.remove(&0);
        set.len() as i32
    }
}
