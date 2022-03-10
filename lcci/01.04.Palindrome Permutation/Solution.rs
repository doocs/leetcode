use std::collections::HashSet;

impl Solution {
    pub fn can_permute_palindrome(s: String) -> bool {
        let mut set = HashSet::new();
        for c in s.chars() {
            if set.contains(&c) {
                set.remove(&c);
            } else {
                set.insert(c);
            }
        }
        set.len() <= 1
    }
}
