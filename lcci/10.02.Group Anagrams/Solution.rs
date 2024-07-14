use std::collections::HashMap;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut map = HashMap::new();
        for s in strs {
            let key = {
                let mut cs = s.chars().collect::<Vec<char>>();
                cs.sort();
                cs.iter().collect::<String>()
            };
            map.entry(key).or_insert(vec![]).push(s);
        }
        map.into_values().collect()
    }
}
