use std::collections::HashSet;

impl Solution {
    pub fn can_permute_palindrome(s: String) -> bool {
        let mut vis = HashSet::new();
        for c in s.chars() {
            if vis.contains(&c) {
                vis.remove(&c);
            } else {
                vis.insert(c);
            }
        }
        vis.len() < 2
    }
}
