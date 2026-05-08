impl Solution {
    pub fn number_of_special_chars(word: String) -> i32 {
        let mut s = [false; 128];
        for ch in word.chars() {
            s[ch as u8 as usize] = true;
        }
        let mut ans = 0;
        for i in 0..26 {
            if s[(b'a' + i) as usize] && s[(b'A' + i) as usize] {
                ans += 1;
            }
        }
        ans
    }
}
