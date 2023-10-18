use std::collections::BinaryHeap;

impl Solution {
    pub fn max_kelements(nums: Vec<i32>, k: i32) -> i64 {
        let mut pq = BinaryHeap::from(nums);
        let mut ans = 0;
        let mut k = k;
        while k > 0 {
            if let Some(v) = pq.pop() {
                ans += v as i64;
                pq.push((v + 2) / 3);
                k -= 1;
            }
        }
        ans
    }
}