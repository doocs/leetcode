impl Solution {
    pub fn is_acronym(words: Vec<String>, s: String) -> bool {
        if words.len() != s.len() {
            return false;
        }
        for (i, word) in words.iter().enumerate() {
            if word.chars().next().unwrap_or_default() != s.chars().nth(i).unwrap_or_default() {
                return false;
            }
        }
        true
    }
}
