use std::collections::HashMap;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut m = HashMap::new();
        for (i, &x) in nums.iter().enumerate() {
            let y = target - x;
            if let Some(&j) = m.get(&y) {
                return vec![j as i32, i as i32];
            }
            m.insert(x, i as i32);
        }
        unreachable!()
    }
}
