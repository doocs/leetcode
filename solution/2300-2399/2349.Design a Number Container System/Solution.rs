use std::collections::{BTreeSet, HashMap};

struct NumberContainers {
    d: HashMap<i32, i32>,
    g: HashMap<i32, BTreeSet<i32>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumberContainers {
    fn new() -> Self {
        Self {
            d: HashMap::new(),
            g: HashMap::new(),
        }
    }

    fn change(&mut self, index: i32, number: i32) {
        if let Some(&old_number) = self.d.get(&index) {
            if let Some(set) = self.g.get_mut(&old_number) {
                set.remove(&index);
            }
        }
        self.d.insert(index, number);
        self.g
            .entry(number)
            .or_insert_with(BTreeSet::new)
            .insert(index);
    }

    fn find(&self, number: i32) -> i32 {
        match self.g.get(&number) {
            Some(set) if !set.is_empty() => *set.iter().next().unwrap(),
            _ => -1,
        }
    }
}
