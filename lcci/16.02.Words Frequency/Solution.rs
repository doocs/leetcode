use std::collections::HashMap;
struct WordsFrequency {
    cnt: HashMap<String, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl WordsFrequency {
    fn new(book: Vec<String>) -> Self {
        let mut cnt = HashMap::new();
        for word in book.into_iter() {
            *cnt.entry(word).or_insert(0) += 1;
        }
        Self { cnt }
    }

    fn get(&self, word: String) -> i32 {
        *self.cnt.get(&word).unwrap_or(&0)
    }
}/**
 * Your WordsFrequency object will be instantiated and called as such:
 * let obj = WordsFrequency::new(book);
 * let ret_1: i32 = obj.get(word);
 */
