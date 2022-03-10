use std::collections::HashSet;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let n = s.len();
        let cs: Vec<char> = s.chars().collect();
        let mut set = HashSet::new();
        let mut l = 0;
        let mut r = 0;
        let mut res = 0;
        while r != n {
            let k = cs[r];
            while set.contains(&k) {
                set.remove(&cs[l]);
                l += 1;
            }
            set.insert(k);
            res = res.max(set.len());
            r += 1;
        }
        res as i32
    }
}
