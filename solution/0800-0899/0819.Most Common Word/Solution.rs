use std::collections::{HashMap, HashSet};
impl Solution {
    pub fn most_common_word(mut paragraph: String, banned: Vec<String>) -> String {
        paragraph.make_ascii_lowercase();
        let banned: HashSet<&str> = banned.iter().map(String::as_str).collect();
        let mut map = HashMap::new();
        for word in paragraph.split(|c| !matches!(c, 'a'..='z')) {
            if word.is_empty() || banned.contains(word) {
                continue;
            }
            let val = map.get(&word).unwrap_or(&0) + 1;
            map.insert(word, val);
        }
        map.into_iter()
            .max_by_key(|&(_, v)| v)
            .unwrap()
            .0
            .to_string()
    }
}
