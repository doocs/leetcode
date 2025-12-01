use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn max_sum(nums: Vec<i32>, threshold: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut idx: Vec<usize> = (0..n).collect();
        idx.sort_by_key(|&i| threshold[i]);

        let mut pq = BinaryHeap::new();
        let mut ans: i64 = 0;
        let mut i = 0;
        let mut step = 1;

        loop {
            while i < n && threshold[idx[i]] <= step {
                pq.push(nums[idx[i]]);
                i += 1;
            }
            match pq.pop() {
                Some(x) => {
                    ans += x as i64;
                    step += 1;
                }
                None => break,
            }
        }

        ans
    }
}
