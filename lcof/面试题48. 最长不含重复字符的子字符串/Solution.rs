use std::collections::HashSet;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut res = 0;
        let mut i = 0;
        let mut set = HashSet::new();
        let chars = s.chars().collect::<Vec<char>>();
        for c in chars.iter() {
            while set.contains(c) {
                set.remove(&chars[i]);
                i += 1;
            }
            set.insert(c);
            res = res.max(set.len())
        }
        res as i32
    }
}
