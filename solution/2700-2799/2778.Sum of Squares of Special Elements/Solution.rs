impl Solution {
    pub fn sum_of_squares(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        for (i, &x) in nums.iter().enumerate() {
            if n % (i + 1) == 0 {
                ans += x * x;
            }
        }
        ans
    }
}
