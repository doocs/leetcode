impl Solution {
    pub fn di_string_match(s: String) -> Vec<i32> {
        let s = s.as_bytes();
        let n = s.len();
        let mut res = Vec::with_capacity(n + 1);
        let (mut low, mut high) = (-1, (n + 1) as i32);
        for i in 0..n {
            res.push(if s[i] == b'I' {
                low += 1;
                low
            } else {
                high -= 1;
                high
            });
        }
        res.push(low + 1);
        res
    }
}
