impl Solution {
    pub fn maximum_difference(nums: Vec<i32>) -> i32 {
        let mut mi = i32::MAX;
        let mut ans = -1;

        for &x in &nums {
            if x > mi {
                ans = ans.max(x - mi);
            } else {
                mi = x;
            }
        }

        ans
    }
}
