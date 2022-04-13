impl Solution {
    pub fn is_anagram(s: String, t: String) -> bool {
        if s.len() != t.len() {
            return false;
        }
        let (s, t) = (s.as_bytes(), t.as_bytes());
        let mut record = [0; 26];
        let n = s.len();
        for i in 0..n {
            record[(s[i] - b'a') as usize] += 1;
            record[(t[i] - b'a') as usize] -= 1;
        }
        record.iter().all(|&c| c == 0)
    }
}
