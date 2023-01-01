impl Solution {
    pub fn repeated_character(s: String) -> char {
        let mut mask = 0;
        for &c in s.as_bytes() {
            if mask & 1 << (c - b'a') as i32 != 0 {
                return c as char;
            }
            mask |= 1 << (c - b'a') as i32;
        }
        ' '
    }
}
