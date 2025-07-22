impl Solution {
    pub fn freq_alphabets(s: String) -> String {
        let s = s.as_bytes();
        let mut ans = String::new();
        let mut i = 0;
        let n = s.len();
        while i < n {
            if i + 2 < n && s[i + 2] == b'#' {
                let num = (s[i] - b'0') * 10 + (s[i + 1] - b'0');
                ans.push((96 + num) as char);
                i += 3;
            } else {
                let num = s[i] - b'0';
                ans.push((96 + num) as char);
                i += 1;
            }
        }
        ans
    }
}
