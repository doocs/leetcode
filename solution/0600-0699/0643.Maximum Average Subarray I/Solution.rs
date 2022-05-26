impl Solution {
    pub fn find_max_average(nums: Vec<i32>, k: i32) -> f64 {
        let k = k as usize;
        let n = nums.len();
        let mut sum = nums.iter().take(k).sum::<i32>();
        let mut max = sum;
        for i in k..n {
            sum += nums[i] - nums[i - k];
            max = max.max(sum);
        }
        f64::from(max) / f64::from(k as i32)
    }
}
