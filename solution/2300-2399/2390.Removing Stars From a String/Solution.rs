impl Solution {
    pub fn remove_stars(s: String) -> String {
        let mut res = String::new();
        for &c in s.as_bytes().iter() {
            if c == b'*' {
                res.pop();
            } else {
                res.push(char::from(c));
            }
        }
        res
    }
}
