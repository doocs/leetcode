use std::collections::HashMap;
struct WordsFrequency {
    counter: HashMap<String, i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl WordsFrequency {

    fn new(book: Vec<String>) -> Self {
        let mut counter = HashMap::new();
        for word in book.into_iter() {
            *counter.entry(word).or_insert(0) += 1;
        }
        Self { counter }
    }

    fn get(&self, word: String) -> i32 {
        *self.counter.get(&word).unwrap_or(&0)
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * let obj = WordsFrequency::new(book);
 * let ret_1: i32 = obj.get(word);
 */