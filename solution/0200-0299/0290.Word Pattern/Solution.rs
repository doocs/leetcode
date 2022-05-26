use std::collections::HashMap;

impl Solution {
    pub fn word_pattern(pattern: String, s: String) -> bool {
        let cs1: Vec<char> = pattern.chars().collect();
        let cs2: Vec<&str> = s.split_whitespace().collect();
        let n = cs1.len();
        if n != cs2.len() {
            return false;
        }
        let mut map1 = HashMap::new();
        let mut map2 = HashMap::new();
        for i in 0..n {
            let c = cs1[i];
            let s = cs2[i];
            if !map1.contains_key(&c) {
                map1.insert(c, i);
            }
            if !map2.contains_key(&s) {
                map2.insert(s, i);
            }
            if map1.get(&c) != map2.get(&s) {
                return false
            }
        }
        true
    }
}
