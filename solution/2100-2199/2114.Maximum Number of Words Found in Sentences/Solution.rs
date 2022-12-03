impl Solution {
    pub fn most_words_found(sentences: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in sentences.iter() {
            let mut count = 1;
            for c in s.as_bytes() {
                if *c == b' ' {
                    count += 1;
                }
            }
            ans = ans.max(count);
        }
        ans
    }
}
