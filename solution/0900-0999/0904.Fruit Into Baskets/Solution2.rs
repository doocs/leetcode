use std::collections::HashMap;
impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let n = fruits.len();
        let mut map = HashMap::new();
        let mut i = 0;
        for &fruit in fruits.iter() {
            *map.entry(fruit).or_insert(0) += 1;
            if map.len() > 2 {
                let k = fruits[i];
                map.insert(k, map[&k] - 1);
                if map[&k] == 0 {
                    map.remove(&k);
                }
                i += 1;
            }
        }
        (n - i) as i32
    }
}
