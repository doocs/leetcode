impl Solution {
    pub fn find_closest(words: Vec<String>, word1: String, word2: String) -> i32 {
        let mut ans = i32::MAX;
        let mut i = -1;
        let mut j = -1;
        for (k, w) in words.iter().enumerate() {
            let k = k as i32;
            if w.eq(&word1) {
                i = k;
            } else if w.eq(&word2) {
                j = k;
            }
            if i != -1 && j != -1 {
                ans = ans.min((i - j).abs());
            }
        }
        ans
    }
}
