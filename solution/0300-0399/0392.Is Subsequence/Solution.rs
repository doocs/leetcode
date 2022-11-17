impl Solution {
    pub fn is_subsequence(s: String, t: String) -> bool {
        let (s, t) = (s.as_bytes(), t.as_bytes());
        let n = t.len();
        let mut i = 0;
        for &c in s.iter() {
            while i < n && t[i] != c {
                i += 1;
            }
            if i == n {
                return false;
            }
            i += 1;
        }
        true
    }
}
