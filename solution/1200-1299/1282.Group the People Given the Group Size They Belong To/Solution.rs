use std::collections::HashMap;
impl Solution {
    pub fn group_the_people(group_sizes: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        let mut map = HashMap::new();
        for i in 0..group_sizes.len() {
            let size = group_sizes[i] as usize;
            let arr = map.entry(size).or_insert(vec![]);
            arr.push(i as i32);
            if arr.len() == size {
                res.push(arr.clone());
                arr.clear();
            }
        }
        res
    }
}
