use std::collections::HashMap;

impl Solution {
    pub fn garbage_collection(garbage: Vec<String>, travel: Vec<i32>) -> i32 {
        let mut last: HashMap<char, usize> = HashMap::new();
        let mut ans = 0;
        for (i, s) in garbage.iter().enumerate() {
            ans += s.len() as i32;
            for c in s.chars() {
                last.insert(c, i);
            }
        }
        let mut ts = 0;
        for (i, t) in travel.iter().enumerate() {
            ts += t;
            for &j in last.values() {
                if i + 1 == j {
                    ans += ts;
                }
            }
        }
        ans
    }
}
