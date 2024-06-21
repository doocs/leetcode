use std::collections::HashMap;

impl Solution {
    pub fn odd_string(words: Vec<String>) -> String {
        let mut h = HashMap::new();

        for w in words {
            let bytes: Vec<i32> = w
                .bytes()
                .zip(w.bytes().skip(1))
                .map(|(current, next)| (next - current) as i32)
                .collect();

            let s: String = bytes.iter().map(|&b| char::from(b as u8)).collect();

            h.entry(s).or_insert(vec![]).push(w);
        }

        for strs in h.values() {
            if strs.len() == 1 {
                return strs[0].clone();
            }
        }

        String::from("")
    }
}
