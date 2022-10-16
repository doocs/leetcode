use std::collections::HashSet;
impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let set = nums.into_iter().collect::<HashSet<i32>>();
        let mut res = -1;
        for &num in set.iter() {
            if set.contains(&(-num)) {
                res = res.max(num);
            }
        }
        res
    }
}
