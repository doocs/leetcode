impl Solution {
    pub fn repeated_character(s: String) -> char {
        let mut vis = [false; 26];
        for &c in s.as_bytes() {
            if vis[(c - b'a') as usize] {
                return c as char;
            }
            vis[(c - b'a') as usize] = true;
        }
        ' '
    }
}
