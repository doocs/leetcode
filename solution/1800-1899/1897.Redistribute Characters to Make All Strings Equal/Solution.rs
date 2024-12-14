impl Solution {
    pub fn make_equal(words: Vec<String>) -> bool {
        let mut cnt = std::collections::HashMap::new();

        for word in words.iter() {
            for c in word.chars() {
                *cnt.entry(c).or_insert(0) += 1;
            }
        }

        let n = words.len();
        cnt.values().all(|&v| v % n == 0)
    }
}
