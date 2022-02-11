use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut l = 0;
        let mut r = nums.len() - 1;
        loop {
            match target.cmp(&(nums[l] + nums[r])) {
                Ordering::Less => r -= 1,
                Ordering::Greater => l += 1,
                Ordering::Equal => break vec![nums[l], nums[r]],
            }
        }
    }
}
