use std::collections::HashMap;

impl Solution {
    pub fn find_lucky(arr: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        arr.iter().for_each(|&x| *cnt.entry(x).or_insert(0) += 1);
        cnt.iter()
            .filter(|(&x, &v)| x == v)
            .map(|(&x, _)| x)
            .max()
            .unwrap_or(-1)
    }
}
