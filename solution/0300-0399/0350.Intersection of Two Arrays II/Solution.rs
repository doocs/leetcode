use std::collections::HashMap;

impl Solution {
    pub fn intersect(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut cnt = HashMap::new();
        for &x in &nums1 {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut ans = Vec::new();
        for &x in &nums2 {
            if let Some(count) = cnt.get_mut(&x) {
                if *count > 0 {
                    ans.push(x);
                    *count -= 1;
                }
            }
        }
        ans
    }
}
