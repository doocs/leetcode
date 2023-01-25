impl Solution {
    pub fn alternate_digit_sum(mut n: i32) -> i32 {
        let mut ans = 0;
        let mut sign = 1;
        while n != 0 {
            ans += (n % 10) * sign;
            sign = -sign;
            n /= 10;
        }
        ans * -sign
    }
}
