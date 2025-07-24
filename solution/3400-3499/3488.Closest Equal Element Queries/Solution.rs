use std::collections::HashMap;

impl Solution {
    pub fn solve_queries(nums: Vec<i32>, queries: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let m = n * 2;
        let mut d = vec![m as i32; m];
        let mut left = HashMap::new();

        for i in 0..m {
            let x = nums[i % n];
            if let Some(&l) = left.get(&x) {
                d[i] = d[i].min((i - l) as i32);
            }
            left.insert(x, i);
        }

        let mut right = HashMap::new();

        for i in (0..m).rev() {
            let x = nums[i % n];
            if let Some(&r) = right.get(&x) {
                d[i] = d[i].min((r - i) as i32);
            }
            right.insert(x, i);
        }

        for i in 0..n {
            d[i] = d[i].min(d[i + n]);
        }

        queries
            .iter()
            .map(|&query| {
                if d[query as usize] >= n as i32 {
                    -1
                } else {
                    d[query as usize]
                }
            })
            .collect()
    }
}
