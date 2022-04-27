impl Solution {
    pub fn max_rotate_function(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let sum: i32 = nums.iter().sum();
        let mut pre: i32 = nums.iter().enumerate().map(|(i, &v)| i as i32 * v).sum();
        (0..n)
            .map(|i| {
                let res = pre;
                pre = pre - (sum - nums[i]) + nums[i] * (n - 1) as i32;
                res
            })
            .max()
            .unwrap_or(0)
    }
}
