use std::collections::HashMap;
use std::iter::FromIterator;

impl Solution {
    pub fn find_restaurant(list1: Vec<String>, list2: Vec<String>) -> Vec<String> {
        let map: HashMap<String, usize> = HashMap::from_iter(list1.into_iter().zip(0..));
        let mut res = vec![];
        let mut min_i = usize::MAX;
        list2.into_iter().enumerate().for_each(|(i, key)| {
            if map.contains_key(&key) {
                let sum_i = map.get(&key).unwrap() + i;
                if sum_i <= min_i {
                    if (sum_i < min_i) {
                        min_i = sum_i;
                        res.clear();
                    }
                    res.push(key);
                }
            }
        });
        res
    }
}
