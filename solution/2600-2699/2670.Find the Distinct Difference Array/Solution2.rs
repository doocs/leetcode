use std::collections::HashSet;

impl Solution {
    pub fn distinct_difference_array(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut s = vec![0; n + 1];
        let mut set = HashSet::new();

        for i in (0..n).rev() {
            set.insert(nums[i]);
            s[i] = set.len();
        }

        let mut ans = Vec::new();
        set.clear();
        for i in 0..n {
            set.insert(nums[i]);
            ans.push((set.len() - s[i + 1]) as i32);
        }

        ans
    }
}
