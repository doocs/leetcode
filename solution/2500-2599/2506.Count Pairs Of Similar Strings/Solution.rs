use std::collections::HashMap;

impl Solution {
    pub fn similar_pairs(words: Vec<String>) -> i32 {
        let mut ans = 0;
        let mut hash: HashMap<i32, i32> = HashMap::new();

        for w in words {
            let mut v = 0;

            for c in w.chars() {
                v |= 1 << ((c as u8) - b'a');
            }

            ans += hash.get(&v).unwrap_or(&0);
            *hash.entry(v).or_insert(0) += 1;
        }

        ans
    }
}
