impl Solution {
    pub fn reverse_left_words(s: String, n: i32) -> String {
        let n = n as usize;
        String::from(&s[n..]) + &s[..n]
    }
}
