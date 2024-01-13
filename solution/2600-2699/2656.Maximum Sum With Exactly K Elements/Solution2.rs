impl Solution {
    pub fn maximize_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mx = *nums.iter().max().unwrap_or(&0);

        ((0 + k - 1) * k) / 2 + k * mx
    }
}
