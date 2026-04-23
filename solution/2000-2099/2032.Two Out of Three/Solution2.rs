use std::collections::HashMap;

impl Solution {
    pub fn two_out_of_three(nums1: Vec<i32>, nums2: Vec<i32>, nums3: Vec<i32>) -> Vec<i32> {
        let mut mask = HashMap::new();
        let all = vec![nums1, nums2, nums3];

        for (i, nums) in all.into_iter().enumerate() {
            for x in nums {
                *mask.entry(x).or_insert(0) |= 1 << i;
            }
        }

        mask.into_iter()
            .filter(|&(_, m)| m & (m - 1) != 0)
            .map(|(x, _)| x)
            .collect()
    }
}
