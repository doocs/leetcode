impl Solution {
    pub fn maximum_sum_score(nums: Vec<i32>) -> i64 {
        let mut l = 0;
        let mut r: i64 = nums.iter().map(|&x| x as i64).sum();
        let mut ans = std::i64::MIN;
        for &x in &nums {
            l += x as i64;
            ans = ans.max(l).max(r);
            r -= x as i64;
        }
        ans
    }
}
