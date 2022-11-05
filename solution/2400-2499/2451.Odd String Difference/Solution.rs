use std::collections::HashMap;
impl Solution {
    pub fn odd_string(words: Vec<String>) -> String {
        let n = words[0].len();
        let mut map: HashMap<String, (bool, usize)> = HashMap::new();
        for (i, word) in words.iter().enumerate() {
            let mut k = String::new();
            for j in 1..n {
                k.push_str(&(word.as_bytes()[j] - word.as_bytes()[j - 1]).to_string());
                k.push(',');
            }
            let new_is_only = !map.contains_key(&k);
            map.insert(k, (new_is_only, i));
        }
        for (is_only, i) in map.values() {
            if *is_only {
                return words[*i].clone();
            }
        }
        String::new()
    }
}
