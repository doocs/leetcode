impl Solution {
    pub fn find_repeat_number(mut nums: Vec<i32>) -> i32 {
        for i in 0..nums.len() {
            while i as i32 != nums[i] {
                let j = nums[i] as usize;
                if nums[i] == nums[j] {
                    return nums[i];
                }
                nums.swap(i, j);
            }
        }
        -1
    }
}
