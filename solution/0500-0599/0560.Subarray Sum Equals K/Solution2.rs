use std::collections::HashMap;

impl Solution {
    pub fn subarray_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut res = 0;
        let mut sum = 0;
        let mut map = HashMap::new();
        map.insert(0, 1);
        nums.iter().for_each(|num| {
            sum += num;
            res += map.get(&(sum - k)).unwrap_or(&0);
            map.insert(sum, map.get(&sum).unwrap_or(&0) + 1);
        });
        res
    }
}
