use std::collections::HashMap;
impl Solution {
    pub fn decode_message(key: String, message: String) -> String {
        let mut d = HashMap::new();
        for c in key.as_bytes() {
            if *c == b' ' || d.contains_key(c) {
                continue;
            }
            d.insert(c, char::from((97 + d.len()) as u8));
        }
        message
            .as_bytes()
            .iter()
            .map(|c| d.get(c).unwrap_or(&' '))
            .collect()
    }
}
