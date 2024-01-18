impl Solution {
    pub fn is_acronym(words: Vec<String>, s: String) -> bool {
        words
            .iter()
            .map(|w| w.chars().next().unwrap_or_default())
            .collect::<String>() == s
    }
}
