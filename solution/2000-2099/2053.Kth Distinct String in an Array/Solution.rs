use std::collections::HashMap;

impl Solution {
    pub fn kth_distinct(arr: Vec<String>, mut k: i32) -> String {
        let mut cnt = HashMap::new();

        for s in &arr {
            *cnt.entry(s).or_insert(0) += 1;
        }

        for s in &arr {
            if *cnt.get(s).unwrap() == 1 {
                k -= 1;
                if k == 0 {
                    return s.clone();
                }
            }
        }

        "".to_string()
    }
}
