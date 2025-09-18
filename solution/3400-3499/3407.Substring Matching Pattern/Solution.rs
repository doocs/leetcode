impl Solution {
    pub fn has_match(s: String, p: String) -> bool {
        let mut i = 0usize;
        for t in p.split('*') {
            if let Some(j) = s[i..].find(t) {
                i += j + t.len();
            } else {
                return false;
            }
        }
        true
    }
}
