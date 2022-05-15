use std::collections::HashMap;
impl Solution {
    pub fn first_uniq_char(s: String) -> char {
        let mut map = HashMap::new();
        for c in s.as_bytes() {
            map.insert(c, !map.contains_key(c));
        }
        for c in s.as_bytes() {
            if map[c] {
                return char::from(*c);
            }
        }
        ' '
    }
}
