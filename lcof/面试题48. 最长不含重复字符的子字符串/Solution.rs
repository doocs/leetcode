use std::collections::HashSet;
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut set = HashSet::new();
        let mut res = 0;
        let mut i = 0;
        for j in 0..n {
            while set.contains(&s[j]) {
                set.remove(&s[i]);
                i += 1;
            }
            set.insert(s[j]);
            res = res.max(set.len());
        }
        res as i32
    }
}
