use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn minimum_difference(nums: Vec<i32>) -> i64 {
        let m = nums.len();
        let n = m / 3;
        let mut s = 0i64;
        let mut pre = vec![0i64; m + 1];
        let mut pq = BinaryHeap::new(); // max-heap

        for i in 1..=2 * n {
            let x = nums[i - 1] as i64;
            s += x;
            pq.push(x);
            if pq.len() > n {
                if let Some(top) = pq.pop() {
                    s -= top;
                }
            }
            pre[i] = s;
        }

        s = 0;
        let mut suf = vec![0i64; m + 1];
        let mut pq = BinaryHeap::new();

        for i in (n + 1..=m).rev() {
            let x = nums[i - 1] as i64;
            s += x;
            pq.push(Reverse(x));
            if pq.len() > n {
                if let Some(Reverse(top)) = pq.pop() {
                    s -= top;
                }
            }
            suf[i] = s;
        }

        let mut ans = i64::MAX;
        for i in n..=2 * n {
            ans = ans.min(pre[i] - suf[i + 1]);
        }

        ans
    }
}
