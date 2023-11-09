use std::collections::HashMap;

impl Solution {
    pub fn max_score(prices: Vec<i32>) -> i64 {
        let mut cnt: HashMap<i32, i64> = HashMap::new();

        for (i, x) in prices.iter().enumerate() {
            let key = (*x as i32) - (i as i32);
            let count = cnt.entry(key).or_insert(0);
            *count += *x as i64;
        }

        *cnt.values().max().unwrap_or(&0)
    }
}
