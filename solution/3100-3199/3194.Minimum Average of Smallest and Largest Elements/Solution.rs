impl Solution {
    pub fn minimum_average(mut nums: Vec<i32>) -> f64 {
        nums.sort();
        let n = nums.len();
        let ans = (0..n / 2).map(|i| nums[i] + nums[n - i - 1]).min().unwrap();
        ans as f64 / 2.0
    }
}
