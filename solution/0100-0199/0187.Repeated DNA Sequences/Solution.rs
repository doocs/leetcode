use std::collections::HashMap;

impl Solution {
    pub fn find_repeated_dna_sequences(s: String) -> Vec<String> {
        let n = s.len();
        let mut res = vec![];
        if n < 10 {
            return res;
        }
        let mut map = HashMap::new();
        for i in 0..=n - 10 {
            let key = &s[i..i + 10];
            if map.contains_key(&key) && *map.get(&key).unwrap() {
                res.push(key.to_string());
            }
            map.insert(key, !map.contains_key(&key));
        }
        res
    }
}
