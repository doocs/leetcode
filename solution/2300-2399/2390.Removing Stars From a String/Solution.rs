impl Solution {
    pub fn remove_stars(s: String) -> String {
        let mut ans = String::new();
        for &c in s.as_bytes().iter() {
            if c == b'*' {
                ans.pop();
            } else {
                ans.push(char::from(c));
            }
        }
        ans
    }
}
