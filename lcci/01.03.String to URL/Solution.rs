impl Solution {
    pub fn replace_spaces(s: String, length: i32) -> String {
        s[..length as usize].replace(' ', "%20")
    }
}
