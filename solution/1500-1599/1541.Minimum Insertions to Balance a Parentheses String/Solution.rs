impl Solution {
    pub fn min_insertions(s: String) -> i32 {
        let mut ans = 0;
        let mut x = 0;
        let mut chars = s.bytes().peekable();
        while let Some(c) = chars.next() {
            if c == b'(' {
                x += 1;
            } else {
                if chars.next_if_eq(&b')').is_none() {
                    ans += 1;
                }
                if x == 0 {
                    ans += 1;
                } else {
                    x -= 1;
                }
            }
        }
        ans + x * 2
    }
}
