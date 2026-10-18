impl Solution {
    pub fn reverse_parentheses(s: String) -> String {
        let mut s = s.into_bytes();
        let mut k = 0;
        for i in 0..s.len() {
            let c = s[i];
            if c == b')' {
                let j = s[..k].iter().rposition(|&ch| ch == b'(').unwrap();
                s[j..k].reverse();
                k -= 1;
            } else {
                s[k] = c;
                k += 1;
            }
        }
        s.truncate(k);
        String::from_utf8(s).unwrap()
    }
}
