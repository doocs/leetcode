use std::collections::HashMap;

impl Solution {
    pub fn uncommon_from_sentences(s1: String, s2: String) -> Vec<String> {
        let mut map = HashMap::new();
        for s in s1.split(' ') {
            map.insert(s, !map.contains_key(s));
        }
        for s in s2.split(' ') {
            map.insert(s, !map.contains_key(s));
        }
        let mut res = Vec::new();
        for (k, v) in map {
            if v {
                res.push(String::from(k))
            }
        }
        res
    }
}
