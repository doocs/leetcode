impl Solution {
    pub fn apply_operations(nums: Vec<i32>) -> Vec<i32> {
        let mut nums = nums;

        for i in 0..nums.len() - 1 {
            if nums[i] == nums[i + 1] {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }

        let mut cur = 0;
        for i in 0..nums.len() {
            if nums[i] != 0 {
                nums.swap(i, cur);
                cur += 1;
            }
        }

        nums
    }
}
