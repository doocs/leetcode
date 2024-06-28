use std::cmp::Reverse;
use std::collections::BinaryHeap;

struct MedianFinder {
    minQ: BinaryHeap<Reverse<i32>>,
    maxQ: BinaryHeap<i32>,
}

impl MedianFinder {
    fn new() -> Self {
        MedianFinder {
            minQ: BinaryHeap::new(),
            maxQ: BinaryHeap::new(),
        }
    }

    fn add_num(&mut self, num: i32) {
        self.maxQ.push(num);
        self.minQ.push(Reverse(self.maxQ.pop().unwrap()));

        if self.minQ.len() > self.maxQ.len() + 1 {
            self.maxQ.push(self.minQ.pop().unwrap().0);
        }
    }

    fn find_median(&self) -> f64 {
        if self.minQ.len() == self.maxQ.len() {
            let min_top = self.minQ.peek().unwrap().0;
            let max_top = *self.maxQ.peek().unwrap();
            (min_top + max_top) as f64 / 2.0
        } else {
            self.minQ.peek().unwrap().0 as f64
        }
    }
}
