use std::collections::HashMap;

impl Solution {
    #[allow(dead_code)]
    pub fn longest_str_chain(words: Vec<String>) -> i32 {
        let mut words = words;
        let mut ret = 0;
        let mut map: HashMap<String, i32> = HashMap::new();

        // Sort the words vector first
        words.sort_by(|lhs, rhs| { lhs.len().cmp(&rhs.len()) });

        // Begin the "dp" process
        for w in words.iter() {
            let n = w.len();
            let mut x = 1;

            for i in 0..n {
                let s = w[..i].to_string() + &w[i + 1..];
                let v = map.entry(s.clone()).or_default();
                x = std::cmp::max(x, *v + 1);
            }

            map.insert(w.clone(), x);

            ret = std::cmp::max(ret, x);
        }

        ret
    }
}
