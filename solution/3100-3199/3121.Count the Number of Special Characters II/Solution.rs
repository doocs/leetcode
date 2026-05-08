impl Solution {
    pub fn number_of_special_chars(word: String) -> i32 {
        let mut first = [0; 128];
        let mut last = [0; 128];
        for (i, ch) in word.chars().enumerate() {
            let j = ch as u8 as usize;
            let pos = (i + 1) as i32;
            if first[j] == 0 {
                first[j] = pos;
            }
            last[j] = pos;
        }
        let mut ans = 0;
        for i in 0..26 {
            let a = (b'a' + i) as usize;
            let b = (b'A' + i) as usize;
            if last[a] > 0 && last[a] < first[b] {
                ans += 1;
            }
        }
        ans
    }
}
