use std::collections::HashMap;
impl Solution {
    pub fn check_if_exist(arr: Vec<i32>) -> bool {
        let mut map = HashMap::new();
        for (i, v) in arr.iter().enumerate() {
            map.insert(v, i);
        }
        for (i, v) in arr.iter().enumerate() {
            if map.contains_key(&(v * 2)) && map[&(v * 2)] != i {
                return true;
            }
        }
        false
    }
}
