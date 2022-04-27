impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        let s = s.trim_end();
        let n = s.len();
        for (i, c) in s.char_indices().rev() {
            if c == ' ' {
                return (n - i - 1) as i32;
            }
        }
        n as i32
    }
}
