use std::collections::HashSet;
impl Solution {
    pub fn find_subarrays(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut set = HashSet::new();
        for i in 1..n {
            if !set.insert(nums[i - 1] + nums[i]) {
                return true;
            }
        }
        false
    }
}
