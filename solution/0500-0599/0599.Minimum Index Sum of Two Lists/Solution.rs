use std::collections::HashMap;

impl Solution {
    pub fn find_restaurant(list1: Vec<String>, list2: Vec<String>) -> Vec<String> {
        let mut d = HashMap::new();
        for (i, s) in list2.iter().enumerate() {
            d.insert(s, i);
        }

        let mut ans = Vec::new();
        let mut mi = std::i32::MAX;

        for (i, s) in list1.iter().enumerate() {
            if let Some(&j) = d.get(s) {
                if (i as i32 + j as i32) < mi {
                    mi = i as i32 + j as i32;
                    ans = vec![s.clone()];
                } else if (i as i32 + j as i32) == mi {
                    ans.push(s.clone());
                }
            }
        }

        ans
    }
}
