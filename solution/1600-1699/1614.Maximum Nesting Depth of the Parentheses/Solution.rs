impl Solution {
    pub fn max_depth(s: String) -> i32 {
        s.bytes()
            .fold((0, 0), |(ans, d), c| match c {
                b'(' => (ans.max(d + 1), d + 1),
                b')' => (ans, d - 1),
                _ => (ans, d),
            })
            .0
    }
}
