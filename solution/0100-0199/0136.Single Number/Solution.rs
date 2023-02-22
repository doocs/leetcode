impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        nums.into_iter().reduce(|r, v| r ^ v).unwrap()
    }
}
