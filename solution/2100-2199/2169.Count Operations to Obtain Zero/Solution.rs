impl Solution {
    pub fn count_operations(mut num1: i32, mut num2: i32) -> i32 {
        let mut ans = 0;
        while num1 != 0 && num2 != 0 {
            ans += 1;
            if num1 >= num2 {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }
        ans
    }
}
