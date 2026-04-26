impl Solution {
    pub fn valid_digit(mut n: i32, x: i32) -> bool {
        let mut has_x = false;
        while n > 9 {
            has_x = has_x || (n % 10 == x);
            n /= 10;
        }
        has_x && (n != x)
    }
}
