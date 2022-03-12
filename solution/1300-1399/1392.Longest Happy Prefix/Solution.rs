impl Solution {
    pub fn longest_prefix(s: String) -> String {
        let n = s.len();
        for i in (0..n).rev() {
            if s[0..i] == s[n - i..n] {
                return s[0..i].to_string();
            }
        }
        String::new()
    }
}
