use std::collections::{ HashMap, BinaryHeap, VecDeque };

impl Solution {
    #[allow(dead_code)]
    pub fn reorganize_string(s: String) -> String {
        let mut map = HashMap::new();
        let mut pq = BinaryHeap::new();
        let mut ret = String::new();
        let mut queue = VecDeque::new();
        let n = s.len();

        // Initialize the HashMap
        for c in s.chars() {
            map.entry(c)
                .and_modify(|e| {
                    *e += 1;
                })
                .or_insert(1);
        }

        // Initialize the binary heap
        for (k, v) in map.iter() {
            if 2 * *v - 1 > n {
                return "".to_string();
            } else {
                pq.push((*v, *k));
            }
        }

        while !pq.is_empty() {
            let (v, k) = pq.pop().unwrap();
            ret.push(k);
            queue.push_back((v - 1, k));
            if queue.len() == 2 {
                let (v, k) = queue.pop_front().unwrap();
                if v != 0 {
                    pq.push((v, k));
                }
            }
        }

        if ret.len() == n {
            ret
        } else {
            "".to_string()
        }
    }
}
