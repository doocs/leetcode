impl Solution {
    pub fn find_max_average(nums: Vec<i32>, k: i32) -> f64 {
        let k = k as usize;
        let n = nums.len();
        let mut s = nums.iter().take(k).sum::<i32>();
        let mut ans = s;
        for i in k..n {
            s += nums[i] - nums[i - k];
            ans = ans.max(s);
        }
        f64::from(ans) / f64::from(k as i32)
    }
}
