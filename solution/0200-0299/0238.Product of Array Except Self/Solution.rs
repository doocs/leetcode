impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let mut dp_left = vec![1_i32; nums.len()];
        let mut dp_right = vec![1_i32; nums.len()];
        for i in 1..nums.len() {
            dp_left[i] = dp_left[i - 1] * nums[i - 1];
        }
        for i in (0..(nums.len() - 1)).rev() {
            dp_right[i] = dp_right[i + 1] * nums[i + 1];
        }
        dp_left
            .into_iter()
            .enumerate()
            .map(|(i, x)| x * dp_right[i])
            .collect()
    }
}
