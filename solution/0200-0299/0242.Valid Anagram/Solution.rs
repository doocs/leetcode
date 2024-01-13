impl Solution {
    pub fn is_anagram(s: String, t: String) -> bool {
        let n = s.len();
        let m = t.len();
        if n != m {
            return false;
        }
        let mut s = s.chars().collect::<Vec<char>>();
        let mut t = t.chars().collect::<Vec<char>>();
        s.sort();
        t.sort();
        for i in 0..n {
            if s[i] != t[i] {
                return false;
            }
        }
        true
    }
}
