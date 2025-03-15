use std::collections::HashSet;

impl Solution {
    pub fn find_pairs(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = HashSet::new();
        let mut vis = HashSet::new();

        for &x in &nums {
            if vis.contains(&(x - k)) {
                ans.insert(x - k);
            }
            if vis.contains(&(x + k)) {
                ans.insert(x);
            }
            vis.insert(x);
        }
        ans.len() as i32
    }
}
