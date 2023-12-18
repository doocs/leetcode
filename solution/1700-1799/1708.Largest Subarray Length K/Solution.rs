impl Solution {
    pub fn largest_subarray(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut j = 0;
        for i in 1..=nums.len() - (k as usize) {
            if nums[i] > nums[j] {
                j = i;
            }
        }
        nums[j..j + (k as usize)].to_vec()
    }
}
