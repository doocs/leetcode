impl Solution {
    pub fn replace_space(s: String) -> String {
        let mut result = String::new();
        for c in s.chars() {
            if c == ' ' {
                result.push_str("%20");
            } else {
                result.push(c);
            }
        }
        result
    }
}
