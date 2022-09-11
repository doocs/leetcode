use std::collections::HashMap;
impl Solution {
    pub fn most_frequent_even(nums: Vec<i32>) -> i32 {
        let mut map = HashMap::new();
        for &num in nums.iter() {
            if num % 2 == 0 {
                *map.entry(num).or_insert(0) += 1;
            }
        }
        if map.len() == 0 {
            return -1;
        }

        let mut res = 0;
        let mut max = 0;
        for (&k, &v) in map.iter() {
            if v > max || (v == max && k < res) {
                max = v;
                res = k;
            }
        }
        res
    }
}
