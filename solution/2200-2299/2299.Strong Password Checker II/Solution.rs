impl Solution {
    pub fn strong_password_checker_ii(password: String) -> bool {
        let s = password.as_bytes();
        let n = password.len();
        if n < 8 {
            return false;
        }
        let mut mask = 0;
        let mut prev = b' ';
        for &c in s.iter() {
            if c == prev {
                return false;
            }
            mask |= if c.is_ascii_uppercase() {
                0b1000
            } else if c.is_ascii_lowercase() {
                0b100
            } else if c.is_ascii_digit() {
                0b10
            } else {
                0b1
            };
            prev = c;
        }
        mask == 0b1111
    }
}
