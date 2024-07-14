use std::collections::HashMap;

struct FrequencyTracker {
    cnt: HashMap<i32, i32>,
    freq: HashMap<i32, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl FrequencyTracker {
    fn new() -> Self {
        FrequencyTracker {
            cnt: HashMap::new(),
            freq: HashMap::new(),
        }
    }

    fn add(&mut self, number: i32) {
        let count = self.cnt.entry(number).or_insert(0);
        let prev_freq = self.freq.entry(*count).or_insert(0);
        *prev_freq -= 1;
        *count += 1;
        let new_freq = self.freq.entry(*count).or_insert(0);
        *new_freq += 1;
    }

    fn delete_one(&mut self, number: i32) {
        if let Some(count) = self.cnt.get_mut(&number) {
            if *count > 0 {
                if let Some(prev_freq) = self.freq.get_mut(count) {
                    *prev_freq -= 1;
                }
                *count -= 1;
                if let Some(new_freq) = self.freq.get_mut(count) {
                    *new_freq += 1;
                }
            }
        }
    }

    fn has_frequency(&self, frequency: i32) -> bool {
        self.freq.get(&frequency).map_or(false, |&freq| freq > 0)
    }
}
