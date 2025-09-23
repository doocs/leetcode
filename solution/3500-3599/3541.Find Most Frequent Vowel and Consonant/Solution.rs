use std::collections::HashMap;

impl Solution {
    pub fn max_freq_sum(s: String) -> i32 {
        let mut cnt: HashMap<char, i32> = HashMap::new();
        for c in s.chars() {
            *cnt.entry(c).or_insert(0) += 1;
        }
        let mut a = 0;
        let mut b = 0;
        for (c, v) in cnt {
            if "aeiou".contains(c) {
                a = a.max(v);
            } else {
                b = b.max(v);
            }
        }
        a + b
    }
}
