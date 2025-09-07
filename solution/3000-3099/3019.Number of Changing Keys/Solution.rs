impl Solution {
    pub fn count_key_changes(s: String) -> i32 {
        let s = s.to_lowercase();
        let bytes = s.as_bytes();
        let mut ans = 0;
        for i in 1..bytes.len() {
            if bytes[i] != bytes[i - 1] {
                ans += 1;
            }
        }
        ans
    }
}
