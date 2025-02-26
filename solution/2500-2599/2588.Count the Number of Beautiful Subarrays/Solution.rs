use std::collections::HashMap;

impl Solution {
    pub fn beautiful_subarrays(nums: Vec<i32>) -> i64 {
        let mut cnt = HashMap::new();
        cnt.insert(0, 1);
        let mut ans = 0;
        let mut mask = 0;
        for &x in nums.iter() {
            mask ^= x;
            ans += *cnt.get(&mask).unwrap_or(&0);
            *cnt.entry(mask).or_insert(0) += 1;
        }
        ans
    }
}
