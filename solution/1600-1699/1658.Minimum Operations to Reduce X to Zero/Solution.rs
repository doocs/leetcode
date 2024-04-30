use std::collections::HashMap;

impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let s = nums.iter().sum::<i32>() - x;
        let mut vis: HashMap<i32, i32> = HashMap::new();
        vis.insert(0, -1);
        let mut mx = -1;
        let mut t = 0;
        for (i, v) in nums.iter().enumerate() {
            t += v;
            if !vis.contains_key(&t) {
                vis.insert(t, i as i32);
            }
            if let Some(&j) = vis.get(&(t - s)) {
                mx = mx.max((i as i32) - j);
            }
        }
        if mx == -1 {
            -1
        } else {
            (nums.len() as i32) - mx
        }
    }
}
