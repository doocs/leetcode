use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn moving_count(m: i32, n: i32, k: i32) -> i32 {
        let mut deque = VecDeque::new();
        let mut set = HashSet::new();
        deque.push_back([0, 0]);
        while let Some([y, x]) = deque.pop_front() {
            if y < m && x < n && !set.contains(&format!("{},{}", y, x)) {
                let str = format!("{}{}", y, x);
                let mut count = 0;
                for c in str.chars() {
                    count += c.to_string().parse::<i32>().unwrap();
                }
                if count <= k {
                    set.insert(format!("{},{}", y, x));
                    deque.push_back([y + 1, x]);
                    deque.push_back([y, x + 1]);
                }
            }
        }
        set.len() as i32
    }
}
