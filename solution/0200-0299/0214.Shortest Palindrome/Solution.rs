impl Solution {
    pub fn shortest_palindrome(s: String) -> String {
        let base = 131;
        let (mut idx, mut prefix, mut suffix, mut mul) = (0, 0, 0, 1);
        for (i, c) in s.chars().enumerate() {
            let t = (c as u64) - ('0' as u64) + 1;
            prefix = prefix * base + t;
            suffix = suffix + t * mul;
            mul *= base;
            if prefix == suffix {
                idx = i + 1;
            }
        }
        if idx == s.len() {
            s
        } else {
            let x: String = (&s[idx..]).chars().rev().collect();
            String::from(x + &s)
        }
    }
}
