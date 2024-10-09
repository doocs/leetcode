use std::collections::HashMap;

impl Solution {
    pub fn similar_pairs(words: Vec<String>) -> i32 {
        let mut ans = 0;
        let mut cnt: HashMap<i32, i32> = HashMap::new();
        for s in words {
            let mut x = 0;
            for c in s.chars() {
                x |= 1 << ((c as u8) - b'a');
            }
            ans += cnt.get(&x).unwrap_or(&0);
            *cnt.entry(x).or_insert(0) += 1;
        }
        ans
    }
}
