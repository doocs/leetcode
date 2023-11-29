use std::collections::BinaryHeap;
use std::cmp::Reverse;

impl Solution {
    pub fn min_build_time(blocks: Vec<i32>, split: i32) -> i32 {
        let mut pq = BinaryHeap::new();

        for x in blocks {
            pq.push(Reverse(x));
        }

        while pq.len() > 1 {
            pq.pop();
            let new_element = pq.pop().unwrap().0 + split;
            pq.push(Reverse(new_element));
        }

        pq.pop().unwrap().0
    }
}
