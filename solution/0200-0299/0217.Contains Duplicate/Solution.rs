use std::collections::HashSet;
impl Solution {
    pub fn contains_duplicate(nums: Vec<i32>) -> bool {
        nums.iter().collect::<HashSet<&i32>>().len() != nums.len()
    }
}
