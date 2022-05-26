impl Solution {
    pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
        let mut len = 0;
        for i in 0..nums.len() {
            if i < 2 || nums[i] != nums[len - 2] {
                nums[len] = nums[i];
                len += 1;
            }
        }
        len as i32
    }
}