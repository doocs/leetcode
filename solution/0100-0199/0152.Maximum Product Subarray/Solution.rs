impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut f = nums[0];
        let mut g = nums[0];
        let mut ans = nums[0];
        for &x in nums.iter().skip(1) {
            let (ff, gg) = (f, g);
            f = x.max(x * ff).max(x * gg);
            g = x.min(x * ff).min(x * gg);
            ans = ans.max(f);
        }
        ans
    }
}
