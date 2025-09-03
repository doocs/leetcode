use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn spellchecker(wordlist: Vec<String>, queries: Vec<String>) -> Vec<String> {
        let s: HashSet<String> = wordlist.iter().cloned().collect();
        let mut low: HashMap<String, String> = HashMap::new();
        let mut pat: HashMap<String, String> = HashMap::new();

        let f = |w: &str| -> String {
            w.chars()
                .map(|c| match c {
                    'a' | 'e' | 'i' | 'o' | 'u' => '*',
                    _ => c,
                })
                .collect()
        };

        for w in &wordlist {
            let mut t = w.to_lowercase();
            if !low.contains_key(&t) {
                low.insert(t.clone(), w.clone());
            }
            t = f(&t);
            if !pat.contains_key(&t) {
                pat.insert(t.clone(), w.clone());
            }
        }

        let mut ans: Vec<String> = Vec::new();
        for query in queries {
            if s.contains(&query) {
                ans.push(query);
                continue;
            }
            let mut q = query.to_lowercase();
            if let Some(v) = low.get(&q) {
                ans.push(v.clone());
                continue;
            }
            q = f(&q);
            if let Some(v) = pat.get(&q) {
                ans.push(v.clone());
                continue;
            }
            ans.push("".to_string());
        }
        ans
    }
}
