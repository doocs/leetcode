impl Solution {
    pub fn array_sign(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        for &num in nums.iter() {
            if num == 0 {
                return 0;
            }
            if num < 0 {
                ans *= -1;
            }
        }
        ans
    }
}
