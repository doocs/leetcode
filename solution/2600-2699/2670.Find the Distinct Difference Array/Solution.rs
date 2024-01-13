use std::collections::HashSet;

impl Solution {
    pub fn distinct_difference_array(nums: Vec<i32>) -> Vec<i32> {
        let mut ans: Vec<i32> = Vec::new();

        for i in 0..nums.len() {
            let mut j = 0;
            let mut hash1 = HashSet::new();
            while j <= i {
                hash1.insert(nums[j]);
                j += 1;
            }

            let mut k = i + 1;
            let mut hash2 = HashSet::new();
            while k < nums.len() {
                hash2.insert(nums[k]);
                k += 1;
            }

            ans.push((hash1.len() - hash2.len()) as i32);
        }

        ans
    }
}
