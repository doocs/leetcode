impl Solution {
    pub fn reverse_left_words(s: String, n: i32) -> String {
        let len = s.len() as i32;
        if n >= len {
            return s;
        }
        String::from(&s[n as usize..]) + &s[..n as usize]
    }
}