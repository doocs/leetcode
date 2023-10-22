impl Solution {
    pub fn maximum_or(nums: Vec<i32>, k: i32) -> i64 {
        let n = nums.len();
        let mut suf = vec![0; n + 1];
        
        for i in (0..n).rev() {
            suf[i] = suf[i + 1] | nums[i] as i64;
        }

        let mut ans = 0i64;
        let mut pre = 0i64;
        let k64 = k as i64;
        for i in 0..n {
            ans = ans.max(pre | ((nums[i] as i64) << k64) | suf[i + 1]);
            pre |= nums[i] as i64;
        }
        
        ans
    }
}