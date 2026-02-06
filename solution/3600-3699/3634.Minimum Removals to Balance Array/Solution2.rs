impl Solution {
    pub fn min_removal(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut ans = n;
        let mut r = 0;
        let k = k as i64;
        for l in 0..n {
            while r < n && nums[r] as i64 <= nums[l] as i64 * k {
                r += 1;
            }
            ans = ans.min(n - (r - l));
        }
        ans as i32
    }
}
