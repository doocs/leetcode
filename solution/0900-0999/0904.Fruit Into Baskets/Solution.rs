use std::collections::HashMap;
impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let n = fruits.len();
        let mut map = HashMap::new();
        let mut res = 0;
        let mut left = 0;
        let mut right = 0;
        while right < n {
            *map.entry(fruits[right]).or_insert(0) += 1;
            right += 1;
            while map.len() > 2 {
                let k = fruits[left];
                map.insert(k, map[&k] - 1);
                if map[&k] == 0 {
                    map.remove(&k);
                }
                left += 1;
            }
            res = res.max(right - left);
        }
        res as i32
    }
}
