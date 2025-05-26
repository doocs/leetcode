use std::collections::HashSet;

impl Solution {
    pub fn count_complete_subarrays(nums: Vec<i32>) -> i32 {
        let mut s = HashSet::new();
        for &x in &nums {
            s.insert(x);
        }
        let cnt = s.len();
        let n = nums.len();
        let mut ans = 0;

        for i in 0..n {
            s.clear();
            for j in i..n {
                s.insert(nums[j]);
                if s.len() == cnt {
                    ans += 1;
                }
            }
        }

        ans
    }
}
