impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        let s = s.trim_end();
        if s.len() == 0 {
            return 0;
        }
        for (i, c) in s.char_indices().rev() {
            if c == ' ' {
                return (s.len() - i - 1) as i32;
            }
        }
        s.len() as i32
    }
}
