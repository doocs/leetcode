use std::collections::HashSet;

impl Solution {
    pub fn minimized_string_length(s: String) -> i32 {
        let mut set = HashSet::new();

        for c in s.chars() {
            set.insert(c);
        }

        set.len() as i32
    }
}
