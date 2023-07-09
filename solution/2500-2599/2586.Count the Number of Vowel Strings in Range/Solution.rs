impl Solution {
    pub fn vowel_strings(words: Vec<String>, left: i32, right: i32) -> i32 {
        let check = |c: u8| -> bool {
            c == b'a' || c == b'e' || c == b'i' || c == b'o' || c == b'u'
        };

        let mut ans = 0;
        for i in left..=right {
            let words_bytes = words[i as usize].as_bytes();
            let first_char = words_bytes[0];
            let last_char = words_bytes[words_bytes.len() - 1];
            if check(first_char) && check(last_char) {
                ans += 1;
            }
        }

        ans
    }
}