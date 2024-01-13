impl Solution {
    pub fn find_the_difference(s: String, t: String) -> char {
        let mut ans = 0;
        for c in s.as_bytes() {
            ans ^= c;
        }
        for c in t.as_bytes() {
            ans ^= c;
        }
        char::from(ans)
    }
}
