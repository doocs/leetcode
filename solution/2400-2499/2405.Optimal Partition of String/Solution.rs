use std::collections::HashSet;
impl Solution {
    pub fn partition_string(s: String) -> i32 {
        let mut set = HashSet::new();
        let mut res = 1;
        for c in s.as_bytes().iter() {
            if set.contains(c) {
                res += 1;
                set.clear();
            }
            set.insert(c);
        }
        res
    }
}
