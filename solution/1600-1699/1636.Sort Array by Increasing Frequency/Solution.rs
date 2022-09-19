use std::collections::HashMap;
impl Solution {
    pub fn frequency_sort(mut nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut map = HashMap::new();
        for &num in nums.iter() {
            *map.entry(num).or_insert(0) += 1;
        }
        nums.sort_by(|a, b| {
            if map.get(a) == map.get(b) {
                return b.cmp(a);
            }
            map.get(a).cmp(&map.get(b))
        });
        nums
    }
}
