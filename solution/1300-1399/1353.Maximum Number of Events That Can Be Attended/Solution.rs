use std::cmp::Reverse;
use std::collections::{BinaryHeap, HashMap};

impl Solution {
    pub fn max_events(events: Vec<Vec<i32>>) -> i32 {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        let mut l = i32::MAX;
        let mut r = 0;

        for event in events {
            let s = event[0];
            let e = event[1];
            g.entry(s).or_default().push(e);
            l = l.min(s);
            r = r.max(e);
        }

        let mut pq = BinaryHeap::new();
        let mut ans = 0;

        for s in l..=r {
            while let Some(&Reverse(top)) = pq.peek() {
                if top < s {
                    pq.pop();
                } else {
                    break;
                }
            }
            if let Some(ends) = g.get(&s) {
                for &e in ends {
                    pq.push(Reverse(e));
                }
            }
            if pq.pop().is_some() {
                ans += 1;
            }
        }

        ans
    }
}
