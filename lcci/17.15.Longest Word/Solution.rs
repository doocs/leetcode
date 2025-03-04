use std::collections::HashSet;

impl Solution {
    pub fn longest_word(words: Vec<String>) -> String {
        let mut s: HashSet<String> = words.iter().cloned().collect();
        let mut words = words;
        words.sort_by(|a, b| b.len().cmp(&a.len()).then(a.cmp(b)));

        fn dfs(w: String, s: &mut HashSet<String>) -> bool {
            if w.is_empty() {
                return true;
            }
            for k in 1..=w.len() {
                if s.contains(&w[0..k]) && dfs(w[k..].to_string(), s) {
                    return true;
                }
            }
            false
        }
        for w in words {
            s.remove(&w);
            if dfs(w.clone(), &mut s) {
                return w;
            }
        }
        String::new()
    }
}
