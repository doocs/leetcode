use std::collections::HashSet;
impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let s = nums.into_iter().collect::<HashSet<i32>>();
        let mut ans = -1;
        for &x in s.iter() {
            if s.contains(&-x) {
                ans = ans.max(x);
            }
        }
        ans
    }
}
