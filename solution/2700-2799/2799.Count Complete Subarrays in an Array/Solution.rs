use std::collections::HashSet;
impl Solution {
    pub fn count_complete_subarrays(nums: Vec<i32>) -> i32 {
        let mut set: HashSet<&i32> = nums.iter().collect();
        let n = nums.len();
        let m = set.len();
        let mut ans = 0;
        for i in 0..n {
            set.clear();
            for j in i..n {
                set.insert(&nums[j]);
                if set.len() == m {
                    ans += n - j;
                    break;
                }
            }
        }
        ans as i32
    }
}
