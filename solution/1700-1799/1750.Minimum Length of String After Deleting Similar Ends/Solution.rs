impl Solution {
    pub fn minimum_length(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut start = 0;
        let mut end = n - 1;
        while start < end && s[start] == s[end] {
            while start + 1 < end && s[start] == s[start + 1] {
                start += 1;
            }
            while start < end - 1 && s[end] == s[end - 1] {
                end -= 1;
            }
            start += 1;
            end -= 1;
        }
        0.max(end - start + 1) as i32
    }
}
