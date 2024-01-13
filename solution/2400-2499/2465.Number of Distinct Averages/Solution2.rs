use std::collections::HashMap;

impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut h = HashMap::new();
        let mut nums = nums;
        let mut ans = 0;
        let n = nums.len();
        nums.sort();

        for i in 0..n >> 1 {
            let x = nums[i] + nums[n - i - 1];
            *h.entry(x).or_insert(0) += 1;

            if *h.get(&x).unwrap() == 1 {
                ans += 1;
            }
        }

        ans
    }
}
