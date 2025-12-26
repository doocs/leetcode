use std::collections::BinaryHeap;

impl Solution {
    pub fn halve_array(nums: Vec<i32>) -> i32 {
        let mut pq: BinaryHeap<i64> = BinaryHeap::new();
        let mut s: i64 = 0;

        for x in nums {
            let v = (x as i64) << 20;
            s += v;
            pq.push(v);
        }

        s /= 2;
        let mut ans = 0;

        while s > 0 {
            let t = pq.pop().unwrap() / 2;
            s -= t;
            pq.push(t);
            ans += 1;
        }

        ans
    }
}
