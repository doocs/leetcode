use std::collections::HashMap;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut d = HashMap::new();
        for (i, &x) in nums.iter().enumerate() {
            let y = target - x;
            if let Some(&j) = d.get(&y) {
                return vec![j as i32, i as i32];
            }
            d.insert(x, i);
        }
        vec![]
    }
}
