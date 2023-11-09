impl Solution {
    pub fn max_absolute_sum(nums: Vec<i32>) -> i32 {
        let mut f = 0;
        let mut g = 0;
        let mut ans = 0;
        for x in nums {
            f = i32::max(f, 0) + x;
            g = i32::min(g, 0) + x;
            ans = i32::max(ans, f.max(-g));
        }
        ans
    }
}
