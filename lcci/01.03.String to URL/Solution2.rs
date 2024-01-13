impl Solution {
    pub fn replace_spaces(s: String, length: i32) -> String {
        s.chars()
            .take(length as usize)
            .map(|c| {
                if c == ' ' { "%20".to_string() } else { c.to_string() }
            })
            .collect()
    }
}
