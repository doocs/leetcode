use std::collections::{ HashSet, VecDeque };
impl Solution {
    pub fn moving_count(m: i32, n: i32, k: i32) -> i32 {
        let mut set = HashSet::new();
        let mut queue = VecDeque::new();
        queue.push_back([0, 0]);
        while let Some([i, j]) = queue.pop_front() {
            let key = format!("{},{}", i, j);
            if
                i == m ||
                j == n ||
                set.contains(&key) ||
                k <
                    format!("{}{}", i, j)
                        .chars()
                        .map(|c| c.to_string().parse::<i32>().unwrap())
                        .sum::<i32>()
            {
                continue;
            }
            set.insert(key);
            queue.push_back([i + 1, j]);
            queue.push_back([i, j + 1]);
        }
        set.len() as i32
    }
}
