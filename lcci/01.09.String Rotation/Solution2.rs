impl Solution {
    pub fn is_fliped_string(s1: String, s2: String) -> bool {
        if s1 == s2 {
            return true;
        }
        if s1.len() != s2.len() {
            return false;
        }
        let s2: Vec<char> = (s2.clone() + &s2).chars().collect();
        let n = s1.len();
        let m = s2.len();
        for i in 0..m - n {
            let mut is_pass = true;
            for (j, c) in s1.chars().enumerate() {
                if c != s2[i + j] {
                    is_pass = false;
                    break;
                }
            }
            if is_pass {
                return true;
            }
        }
        false
    }
}
