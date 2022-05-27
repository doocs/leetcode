impl Solution {
    pub fn find_closest(words: Vec<String>, word1: String, word2: String) -> i32 {
        let mut res = i32::MAX;
        let mut index1 = -1;
        let mut index2 = -1;
        for (i, word) in words.iter().enumerate() {
            let i = i as i32;
            if word.eq(&word1) {
                index1 = i;
            } else if word.eq(&word2) {
                index2 = i;
            }
            if index1 != -1 && index2 != -1 {
                res = res.min((index1 - index2).abs());
            }
        }
        res
    }
}
