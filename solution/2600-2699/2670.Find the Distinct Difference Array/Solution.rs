use std::collections::HashSet;

impl Solution {
    pub fn distinct_difference_array(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut suf = vec![0; n + 1];
        let mut s = HashSet::new();

        for i in (0..n).rev() {
            s.insert(nums[i]);
            suf[i] = s.len();
        }

        let mut ans = Vec::new();
        s.clear();
        for i in 0..n {
            s.insert(nums[i]);
            ans.push((s.len() - suf[i + 1]) as i32);
        }

        ans
    }
}
