use std::collections::HashMap;
impl Solution {
    pub fn find_and_replace_pattern(words: Vec<String>, pattern: String) -> Vec<String> {
        let pattern = pattern.as_bytes();
        let n = pattern.len();
        words
            .into_iter()
            .filter(|word| {
                let word = word.as_bytes();
                let mut map1 = HashMap::new();
                let mut map2 = HashMap::new();
                for i in 0..n {
                    if map1.get(&word[i]).unwrap_or(&n) != map2.get(&pattern[i]).unwrap_or(&n) {
                        return false;
                    }
                    map1.insert(word[i], i);
                    map2.insert(pattern[i], i);
                }
                true
            })
            .collect()
    }
}
