use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn number_game(nums: Vec<i32>) -> Vec<i32> {
        let mut pq = BinaryHeap::new();

        for &x in &nums {
            pq.push(Reverse(x));
        }

        let mut ans = Vec::new();

        while let Some(Reverse(a)) = pq.pop() {
            if let Some(Reverse(b)) = pq.pop() {
                ans.push(b);
                ans.push(a);
            }
        }

        ans
    }
}
