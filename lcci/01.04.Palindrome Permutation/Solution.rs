use std::collections::HashMap;

impl Solution {
    pub fn can_permute_palindrome(s: String) -> bool {
        let mut cnt = HashMap::new();
        for c in s.chars() {
            *cnt.entry(c).or_insert(0) += 1;
        }
        cnt.values().filter(|&&v| v % 2 == 1).count() < 2
    }
}
