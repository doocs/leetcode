use std::collections::HashMap;

impl Solution {
    pub fn tuple_same_product(nums: Vec<i32>) -> i32 {
        let mut cnt: HashMap<i32, i32> = HashMap::new();
        let mut ans = 0;

        for i in 1..nums.len() {
            for j in 0..i {
                let x = nums[i] * nums[j];
                *cnt.entry(x).or_insert(0) += 1;
            }
        }

        for v in cnt.values() {
            ans += v * (v - 1) / 2;
        }

        ans << 3
    }
}