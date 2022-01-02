impl Solution {
    pub fn remove_element(nums: &mut Vec<i32>, val: i32) -> i32 {
        let mut len = 0;
        for i in 0..nums.len() {
            if nums[i] != val {
                nums[len] = nums[i];
                len += 1;
            }
        }
        len as i32
    }
}
