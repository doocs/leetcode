impl Solution {
    pub fn is_palindrome(s: String) -> bool {
        let ss: Vec<char> = s.chars().collect();
        let mut l = 0;
        let mut r = ss.len() - 1;
        while l < r {
            while l < r && !(ss[l].is_alphabetic() || ss[l].is_numeric()) {
                l += 1;
            }
            while l < r && !(ss[r].is_alphabetic() || ss[r].is_numeric()) {
                r -= 1;
            }
            if ss[l].to_ascii_lowercase() != ss[r].to_ascii_lowercase() {
                return false;
            }
            // 防止 usize 破界
            if r == 0 {
                return true;
            }
            l += 1;
            r -= 1;
        }
        true
    }
}
