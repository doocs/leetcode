use std::collections::HashMap;

pub fn soluation(nums: Vec<i32>, target: i32) -> Vec<i32> {
    let mut map = HashMap::new();
    for (i, item) in nums.iter().enumerate() {
        if map.contains_key(item) {
            return vec![i as i32, map[item]];
        } else {
            let x = target - nums[i];
            map.insert(x, i as i32);
        }
    }
    unreachable!()
}
