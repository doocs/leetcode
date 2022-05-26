impl Solution {
    pub fn add_binary(a: String, b: String) -> String {
        let n = a.len().max(b.len());
        let (a, b) = (a.as_bytes(), b.as_bytes());
        let mut res = vec![];
        let mut is_over = false;
        let mut i = 0;
        while i < n || is_over {
            let mut val = if is_over { 1 } else { 0 };
            is_over = false;
            if a.get(a.len() - i - 1).unwrap_or(&b'0') == &b'1' {
                val += 1;
            }
            if b.get(b.len() - i - 1).unwrap_or(&b'0') == &b'1' {
                val += 1;
            }
            if val > 1 {
                is_over = true;
                val -= 2;
            }
            i += 1;
            res.push(char::from(b'0' + val));
        }
        res.iter().rev().collect()
    }
}
