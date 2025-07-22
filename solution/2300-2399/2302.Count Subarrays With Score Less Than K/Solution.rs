impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, k: i64) -> i64 {
        let n = nums.len();
        let mut s = vec![0i64; n + 1];
        for i in 0..n {
            s[i + 1] = s[i] + nums[i] as i64;
        }
        let mut ans = 0i64;
        for i in 1..=n {
            let mut l = 0;
            let mut r = i;
            while l < r {
                let mid = (l + r + 1) / 2;
                let sum = s[i] - s[i - mid];
                if sum * (mid as i64) < k {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            ans += l as i64;
        }
        ans
    }
}
