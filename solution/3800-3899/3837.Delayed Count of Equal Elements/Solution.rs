use std::collections::HashMap;

impl Solution {
    pub fn delayed_count(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut cnt: HashMap<i32, i32> = HashMap::new();
        let mut ans = vec![0; n];
        let k = k as usize;
        let mut i = n as i32 - k as i32 - 2;
        while i >= 0 {
            let idx = i as usize;
            *cnt.entry(nums[idx + k + 1]).or_insert(0) += 1;
            ans[idx] = *cnt.get(&nums[idx]).unwrap_or(&0);
            i -= 1;
        }
        ans
    }
}
