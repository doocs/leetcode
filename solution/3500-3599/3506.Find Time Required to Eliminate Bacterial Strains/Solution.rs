use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_elimination_time(time_req: Vec<i32>, split_time: i32) -> i64 {
        let mut pq = BinaryHeap::new();
        for x in time_req {
            pq.push(Reverse(x as i64));
        }
        while pq.len() > 1 {
            pq.pop();
            let merged = pq.pop().unwrap().0 + split_time as i64;
            pq.push(Reverse(merged));
        }
        pq.pop().unwrap().0
    }
}
