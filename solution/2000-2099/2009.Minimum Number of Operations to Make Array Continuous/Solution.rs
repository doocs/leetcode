use std::collections::BTreeSet;

impl Solution {
    #[allow(dead_code)]
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let nums = nums.into_iter().collect::<BTreeSet<i32>>();

        let m = nums.len();
        let nums = nums.into_iter().collect::<Vec<i32>>();

        let mut ans = n;

        for i in 0..m {
            let j = match nums.binary_search(&(nums[i] + (n as i32))) {
                Ok(idx) => idx,
                Err(idx) => idx,
            };
            ans = std::cmp::min(ans, n - (j - i));
        }

        ans as i32
    }
}
