use std::collections::HashMap;
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut map = HashMap::new();
        let mut res = 0;
        let mut i = -1;
        for j in 0..n {
            let c = s[j];
            let j = j as i32;
            if map.contains_key(&c) {
                i = i.max(*map.get(&c).unwrap());
            }
            map.insert(c, j);
            res = res.max(j - i);
        }
        res
    }
}
