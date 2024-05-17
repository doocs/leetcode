use std::collections::HashMap;

impl Solution {
    pub fn subarray_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        cnt.insert(0, 1);
        let mut ans = 0;
        let mut s = 0;
        for &x in &nums {
            s += x;
            if let Some(&v) = cnt.get(&(s - k)) {
                ans += v;
            }
            *cnt.entry(s).or_insert(0) += 1;
        }
        ans
    }
}
