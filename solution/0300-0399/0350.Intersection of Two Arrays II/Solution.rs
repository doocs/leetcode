use std::collections::HashMap;
impl Solution {
    pub fn intersect(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut map = HashMap::new();
        for num in nums1.iter() {
            *map.entry(num).or_insert(0) += 1;
        }

        let mut res = vec![];
        for num in nums2.iter() {
            if map.contains_key(num) && map.get(num).unwrap() != &0 {
                map.insert(num, map.get(&num).unwrap() - 1);
                res.push(*num);
            }
        }
        res
    }
}
