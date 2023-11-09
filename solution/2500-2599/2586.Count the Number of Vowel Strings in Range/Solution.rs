impl Solution {
    pub fn vowel_strings(words: Vec<String>, left: i32, right: i32) -> i32 {
        let check = |c: u8| -> bool {
            c == b'a' || c == b'e' || c == b'i' || c == b'o' || c == b'u'
        };

        let mut ans = 0;
        for i in left..=right {
            let w = words[i as usize].as_bytes();
            if check(w[0]) && check(w[w.len() - 1]) {
                ans += 1;
            }
        }

        ans
    }
}
