use std::collections::HashSet;
impl Solution {
    pub fn two_out_of_three(nums1: Vec<i32>, nums2: Vec<i32>, nums3: Vec<i32>) -> Vec<i32> {
        let mut count = vec![0; 101];
        nums1
            .into_iter()
            .collect::<HashSet<i32>>()
            .iter()
            .for_each(|&v| count[v as usize] += 1);
        nums2
            .into_iter()
            .collect::<HashSet<i32>>()
            .iter()
            .for_each(|&v| count[v as usize] += 1);
        nums3
            .into_iter()
            .collect::<HashSet<i32>>()
            .iter()
            .for_each(|&v| count[v as usize] += 1);
        let mut ans = Vec::new();
        count.iter().enumerate().for_each(|(i, v)| {
            if *v >= 2 {
                ans.push(i as i32);
            }
        });
        ans
    }
}
