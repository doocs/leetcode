use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let mut s: HashSet<i32> = nums.iter().cloned().collect();
        let mut ans = 0;
        let mut d: HashMap<i32, i32> = HashMap::new();
        for &x in &nums {
            let mut y = x;
            while s.contains(&y) {
                s.remove(&y);
                y += 1;
            }
            let length = d.get(&(y)).unwrap_or(&0) + y - x;
            d.insert(x, length);
            ans = ans.max(length);
        }
        ans
    }
}
