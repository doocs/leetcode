impl Solution {
    pub fn maximum_triplet_value(nums: Vec<i32>) -> i64 {
        let mut ans: i64 = 0;
        let mut mx: i32 = 0;
        let mut mx_diff: i32 = 0;

        for &x in &nums {
            ans = ans.max(mx_diff as i64 * x as i64);
            mx_diff = mx_diff.max(mx - x);
            mx = mx.max(x);
        }

        ans
    }
}
