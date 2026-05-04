use std::collections::HashMap;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut d = HashMap::new();
        for s in strs {
            let mut cnt = [0; 26];
            for c in s.chars() {
                cnt[(c as usize) - ('a' as usize)] += 1;
            }
            d.entry(cnt).or_insert_with(Vec::new).push(s);
        }
        d.into_values().collect()
    }
}
