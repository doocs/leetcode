impl Solution {
    pub fn max_rotate_function(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let sum: i64 = nums.iter().map(|&v| v as i64).sum();
        let mut pre: i64 = nums
            .iter()
            .enumerate()
            .map(|(i, &v)| i as i64 * v as i64)
            .sum();
        (0..n)
            .map(|i| {
                let res = pre;
                pre = pre - (sum - nums[i] as i64) + nums[i] as i64 * (n as i64 - 1);
                res
            })
            .max()
            .unwrap_or(0) as i32
    }
}
