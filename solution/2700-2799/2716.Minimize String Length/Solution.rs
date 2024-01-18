use std::collections::HashMap;

impl Solution {
    pub fn minimized_string_length(s: String) -> i32 {
        let mut hash = HashMap::new();

        for c in s.chars() {
            hash.insert(c, true);
        }

        hash.len() as i32
    }
}
