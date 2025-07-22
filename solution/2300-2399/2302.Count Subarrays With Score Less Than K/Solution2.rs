impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, k: i64) -> i64 {
        let mut ans = 0i64;
        let mut s = 0i64;
        let mut j = 0;

        for i in 0..nums.len() {
            s += nums[i] as i64;
            while s * (i as i64 - j as i64 + 1) >= k {
                s -= nums[j] as i64;
                j += 1;
            }
            ans += i as i64 - j as i64 + 1;
        }

        ans
    }
}
