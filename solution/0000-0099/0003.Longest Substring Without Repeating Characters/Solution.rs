use std::collections::HashSet;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let mut ss = HashSet::new();
        let mut i = 0;
        s.iter()
            .map(|c| {
                while ss.contains(&c) {
                    ss.remove(&s[i]);
                    i += 1;
                }
                ss.insert(c);
                ss.len()
            })
            .max()
            .unwrap_or(0) as i32
    }
}
