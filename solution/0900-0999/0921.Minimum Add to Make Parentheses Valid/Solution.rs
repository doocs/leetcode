impl Solution {
    pub fn min_add_to_make_valid(s: String) -> i32 {
        let (ans, cnt) = s.bytes().fold((0, 0), |(ans, cnt), c| {
            if c == b'(' {
                (ans, cnt + 1)
            } else if cnt > 0 {
                (ans, cnt - 1)
            } else {
                (ans + 1, cnt)
            }
        });
        ans + cnt
    }
}
