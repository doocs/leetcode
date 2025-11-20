impl Solution {
    pub fn max_subarray_sum(nums: Vec<i32>, k: i32) -> i64 {
        let k = k as usize;
        let inf = 1i64 << 62;
        let mut f = vec![inf; k];
        f[k - 1] = 0;
        let mut s = 0i64;
        let mut ans = -inf;
        for (i, &x) in nums.iter().enumerate() {
            s += x as i64;
            ans = ans.max(s - f[i % k]);
            f[i % k] = f[i % k].min(s);
        }
        ans
    }
}
