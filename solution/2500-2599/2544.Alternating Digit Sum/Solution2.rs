impl Solution {
    pub fn alternate_digit_sum(n: i32) -> i32 {
        let mut ans = 0;
        let mut sign = 1;

        for c in format!("{}", n).chars() {
            let x = c.to_digit(10).unwrap() as i32;
            ans += x * sign;
            sign *= -1;
        }

        ans
    }
}
