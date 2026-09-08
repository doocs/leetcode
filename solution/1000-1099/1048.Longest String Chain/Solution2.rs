use std::collections::HashMap;

impl Solution {
    pub fn longest_str_chain(mut words: Vec<String>) -> i32 {
        words.sort_unstable_by_key(|w| w.len());
        let mut f = HashMap::new();
        let mut ans = 0;
        for w in words {
            let mut x = 1;
            for i in 0..w.len() {
                let pred = format!("{}{}", &w[..i], &w[i + 1..]);
                x = x.max(f.get(&pred).copied().unwrap_or(0) + 1);
            }
            f.insert(w, x);
            ans = ans.max(x);
        }
        ans
    }
}
