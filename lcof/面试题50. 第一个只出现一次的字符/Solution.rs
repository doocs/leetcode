use std::collections::HashMap;

impl Solution {
    pub fn first_uniq_char(s: String) -> char {
        let mut map = HashMap::new();
        let s = s.chars().collect::<Vec<char>>();
        for c in s.iter() {
            match map.contains_key(c) {
                true => map.insert(c, false),
                false => map.insert(c, true),
            };
        }
        for c in s.iter() {
            if let Some(is_single) = map.get(c) {
                if *is_single {
                    return *c;
                }
            }
        }
        ' '
    }
}
