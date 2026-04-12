use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_interval(intervals: Vec<Vec<i32>>, queries: Vec<i32>) -> Vec<i32> {
        let mut intervals = intervals;
        intervals.sort_by_key(|v| v[0]);

        let mut sorted_queries: Vec<(i32, usize)> = queries
            .into_iter()
            .enumerate()
            .map(|(i, x)| (x, i))
            .collect();
        sorted_queries.sort_by_key(|&(x, _)| x);

        let mut ans = vec![-1; sorted_queries.len()];
        let mut heap: BinaryHeap<Reverse<(i32, i32)>> = BinaryHeap::new();
        let mut i = 0usize;

        for (x, idx) in sorted_queries {
            while i < intervals.len() && intervals[i][0] <= x {
                let l = intervals[i][0];
                let r = intervals[i][1];
                heap.push(Reverse((r - l + 1, r)));
                i += 1;
            }

            while let Some(&Reverse((_, r))) = heap.peek() {
                if r < x {
                    heap.pop();
                } else {
                    break;
                }
            }

            if let Some(&Reverse((len, _))) = heap.peek() {
                ans[idx] = len;
            }
        }

        ans
    }
}
