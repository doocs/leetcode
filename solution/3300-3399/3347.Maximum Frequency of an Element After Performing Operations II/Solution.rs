use std::collections::{HashMap, BTreeMap};

impl Solution {
    pub fn max_frequency(nums: Vec<i32>, k: i32, num_operations: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut d = BTreeMap::new();

        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
            d.entry(x).or_insert(0);
            *d.entry(x - k).or_insert(0) += 1;
            *d.entry(x + k + 1).or_insert(0) -= 1;
        }

        let mut ans = 0;
        let mut s = 0;
        for (&x, &t) in d.iter() {
            s += t;
            let cur = s.min(cnt.get(&x).copied().unwrap_or(0) + num_operations);
            ans = ans.max(cur);
        }

        ans
    }
}
