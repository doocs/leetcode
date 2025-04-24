use std::collections::HashMap;

impl Solution {
    pub fn count_interesting_subarrays(nums: Vec<i32>, modulo: i32, k: i32) -> i64 {
        let mut arr: Vec<i32> = nums
            .iter()
            .map(|&x| if x % modulo == k { 1 } else { 0 })
            .collect();
        let mut cnt: HashMap<i32, i64> = HashMap::new();
        cnt.insert(0, 1);

        let mut ans: i64 = 0;
        let mut s: i32 = 0;

        for x in arr {
            s += x;
            let key = (s - k).rem_euclid(modulo);
            ans += *cnt.get(&key).unwrap_or(&0);
            *cnt.entry(s % modulo).or_insert(0) += 1;
        }

        ans
    }
}
