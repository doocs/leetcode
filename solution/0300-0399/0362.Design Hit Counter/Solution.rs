use std::{ collections::BinaryHeap, cmp::Reverse };

struct HitCounter {
    /// A min heap
    pq: BinaryHeap<Reverse<i32>>,
}

impl HitCounter {
    fn new() -> Self {
        Self {
            pq: BinaryHeap::new(),
        }
    }

    fn hit(&mut self, timestamp: i32) {
        self.pq.push(Reverse(timestamp));
    }

    fn get_hits(&mut self, timestamp: i32) -> i32 {
        while let Some(Reverse(min_elem)) = self.pq.peek() {
            if *min_elem <= timestamp - 300 {
                self.pq.pop();
            } else {
                break;
            }
        }

        self.pq.len() as i32
    }
}
