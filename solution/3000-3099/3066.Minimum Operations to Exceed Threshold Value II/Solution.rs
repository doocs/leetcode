use std::collections::BinaryHeap;

impl Solution {
    pub fn min_operations(nums: Vec<i32>, k: i32) -> i32 {
        let mut pq = BinaryHeap::new();

        for &x in &nums {
            pq.push(-(x as i64));
        }

        let mut ans = 0;

        while pq.len() > 1 && -pq.peek().unwrap() < k as i64 {
            let x = -pq.pop().unwrap();
            let y = -pq.pop().unwrap();
            pq.push(-(x * 2 + y));
            ans += 1;
        }

        ans
    }
}
