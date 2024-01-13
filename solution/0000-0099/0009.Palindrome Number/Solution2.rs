impl Solution {
    pub fn is_palindrome(mut x: i32) -> bool {
        if x < 0 || (x % 10 == 0 && x != 0) {
            return false;
        }
        let mut y = 0;
        while x > y {
            y *= 10;
            y += x % 10;
            x /= 10;
        }
        x == y || x == y / 10
    }
}
