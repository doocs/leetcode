impl Solution {
    pub fn add_binary(a: String, b: String) -> String {
        let mut res = String::new();
        let a = a.as_bytes();
        let b = b.as_bytes();
        let mut i = a.len();
        let mut j = b.len();
        let mut is_over = false;
        while i != 0 || j != 0 || is_over {
            let mut sum = if is_over { b'1' } else { b'0' };
            if i != 0 {
                sum += a[i - 1] - b'0';
                i -= 1;
            }
            if j != 0 {
                sum += b[j - 1] - b'0';
                j -= 1;
            }
            is_over = if sum > b'1' {
                sum -= b'2' - b'0';
                true
            } else {
                false
            };
            res.push(char::from(sum));
        }
        res.chars().rev().collect()
    }
}
