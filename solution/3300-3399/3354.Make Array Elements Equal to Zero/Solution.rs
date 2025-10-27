impl Solution {
    pub fn count_valid_selections(nums: Vec<i32>) -> i32 {
        let s: i32 = nums.iter().sum();
        let mut ans = 0;
        let mut l = 0;
        for &x in &nums {
            if x != 0 {
                l += x;
            } else if l * 2 == s {
                ans += 2;
            } else if (l * 2 - s).abs() <= 1 {
                ans += 1;
            }
        }
        ans
    }
}
