use std::collections::HashSet;
impl Solution {
    pub fn halves_are_alike(s: String) -> bool {
        let set: HashSet<&u8> = [b'a', b'e', b'i', b'o', b'u', b'A', b'E', b'I', b'O', b'U']
            .into_iter()
            .collect();
        let s = s.as_bytes();
        let n = s.len() >> 1;
        let mut count = 0;
        for i in 0..n {
            if set.contains(&s[i]) {
                count += 1;
            }
            if set.contains(&s[n + i]) {
                count -= 1;
            }
        }
        count == 0
    }
}
