use std::collections::HashSet;
impl Solution {
    pub fn find_error_nums(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() as i32;
        let s1 = ((1 + n) * n) / 2;
        let s2 = nums
            .iter()
            .cloned()
            .collect::<HashSet<i32>>()
            .iter()
            .sum::<i32>();
        let s: i32 = nums.iter().sum();
        vec![s - s2, s1 - s2]
    }
}
