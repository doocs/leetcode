use std::collections::HashSet;
impl Solution {
    pub fn num_different_integers(word: String) -> i32 {
        let s = word.as_bytes();
        let n = s.len();
        let mut set = HashSet::new();
        let mut i = 0;
        while i < n {
            if s[i] >= b'0' && s[i] <= b'9' {
                let mut j = i;
                while j < n && s[j] >= b'0' && s[j] <= b'9' {
                    j += 1;
                }
                while i < j - 1 && s[i] == b'0' {
                    i += 1;
                }
                set.insert(String::from_utf8(s[i..j].to_vec()).unwrap());
                i = j;
            } else {
                i += 1;
            }
        }
        set.len() as i32
    }
}
