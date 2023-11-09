use std::collections::HashSet;

impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut set = HashSet::new();
        let mut ans = 0;
        let n = nums.len();
        let mut nums = nums;
        nums.sort();

        for i in 0..n >> 1 {
            let x = nums[i] + nums[n - i - 1];

            if set.contains(&x) {
                continue;
            }

            set.insert(x);
            ans += 1;
        }

        ans
    }
}
