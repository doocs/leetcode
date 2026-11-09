use std::cmp::Reverse;
use std::collections::{BTreeSet, BinaryHeap};

impl Solution {
    pub fn busiest_servers(k: i32, arrival: Vec<i32>, load: Vec<i32>) -> Vec<i32> {
        let mut free: BTreeSet<i32> = (0..k).collect();
        let mut busy = BinaryHeap::new();
        let mut cnt = vec![0; k as usize];
        for i in 0..arrival.len() {
            let start = arrival[i];
            let end = start + load[i];
            loop {
                match busy.peek() {
                    Some(&Reverse((t, server))) if t <= start => {
                        busy.pop();
                        free.insert(server);
                    }
                    _ => break,
                }
            }
            if free.is_empty() {
                continue;
            }
            let j = i as i32 % k;
            let server = match free.range(j..).next() {
                Some(&server) => server,
                None => *free.iter().next().unwrap(),
            };
            cnt[server as usize] += 1;
            busy.push(Reverse((end, server)));
            free.remove(&server);
        }
        let mx = *cnt.iter().max().unwrap();
        (0..k).filter(|&i| cnt[i as usize] == mx).collect()
    }
}
