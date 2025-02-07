impl Solution {
    pub fn max_value(n: String, x: i32) -> String {
        let s = n.as_bytes();
        let mut i = 0;
        if n.starts_with('-') {
            i += 1;
            while i < s.len() && (s[i] - b'0') as i32 <= x {
                i += 1;
            }
        } else {
            while i < s.len() && (s[i] - b'0') as i32 >= x {
                i += 1;
            }
        }
        let mut ans = String::new();
        ans.push_str(&n[0..i]);
        ans.push_str(&x.to_string());
        ans.push_str(&n[i..]);
        ans
    }
}
