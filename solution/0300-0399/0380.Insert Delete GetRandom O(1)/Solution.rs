use rand::Rng;
use std::collections::HashMap;

struct RandomizedSet {
    d: HashMap<i32, usize>,
    q: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl RandomizedSet {
    fn new() -> Self {
        Self {
            d: HashMap::new(),
            q: Vec::new(),
        }
    }

    fn insert(&mut self, val: i32) -> bool {
        if self.d.contains_key(&val) {
            return false;
        }
        self.d.insert(val, self.q.len());
        self.q.push(val);
        true
    }

    fn remove(&mut self, val: i32) -> bool {
        if !self.d.contains_key(&val) {
            return false;
        }
        let i = self.d[&val];
        let last = *self.q.last().unwrap();
        self.d.insert(last, i);
        self.q[i] = last;
        self.q.pop();
        self.d.remove(&val);
        true
    }

    fn get_random(&self) -> i32 {
        let i = rand::thread_rng().gen_range(0..self.q.len());
        self.q[i]
    }
}
