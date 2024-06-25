use std::collections::BTreeSet;

struct SmallestInfiniteSet {
    s: BTreeSet<i32>,
}

impl SmallestInfiniteSet {
    fn new() -> Self {
        let mut set = BTreeSet::new();
        for i in 1..=1000 {
            set.insert(i);
        }
        SmallestInfiniteSet { s: set }
    }

    fn pop_smallest(&mut self) -> i32 {
        let x = *self.s.iter().next().unwrap();
        self.s.remove(&x);
        x
    }

    fn add_back(&mut self, num: i32) {
        self.s.insert(num);
    }
}
