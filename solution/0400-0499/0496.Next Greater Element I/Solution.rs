use std::collections::HashMap;

impl Solution {
    pub fn next_greater_element(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut map = HashMap::new();
        let mut stack = Vec::new();
        for num in nums2 {
            while num > *stack.last().unwrap_or(&i32::MAX) {
                map.insert(stack.pop().unwrap(), num);
            }
            stack.push(num);
        }
        nums1
            .iter()
            .map(|n| *map.get(n).unwrap_or(&-1))
            .collect::<Vec<i32>>()
    }
}
