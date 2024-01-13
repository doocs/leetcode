use std::collections::HashMap;
impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        let n = s1.len();
        let m = s2.len();
        if n != m {
            return false;
        }
        let s1 = s1.as_bytes();
        let s2 = s2.as_bytes();
        let mut map = HashMap::new();
        for i in 0..n {
            *map.entry(s1[i]).or_insert(0) += 1;
            *map.entry(s2[i]).or_insert(0) -= 1;
        }
        map.values().all(|i| *i == 0)
    }
}
