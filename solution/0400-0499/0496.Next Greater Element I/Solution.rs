use std::collections::HashMap;

impl Solution {
    pub fn next_greater_element(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut stk = Vec::new();
        let mut d = HashMap::new();
        for &x in nums2.iter().rev() {
            while let Some(&top) = stk.last() {
                if top <= x {
                    stk.pop();
                } else {
                    break;
                }
            }
            if let Some(&top) = stk.last() {
                d.insert(x, top);
            }
            stk.push(x);
        }

        nums1
            .into_iter()
            .map(|x| *d.get(&x).unwrap_or(&-1))
            .collect()
    }
}
