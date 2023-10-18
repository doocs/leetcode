impl Solution {
    pub fn can_be_increasing(nums: Vec<i32>) -> bool {
        fn check(nums: &Vec<i32>, i: usize) -> bool {
            let mut prev= i32::MIN;
            for j in 0..nums.len() {
                if i != j {
                    if prev >= nums[j] {
                        return false
                    }
                    prev = nums[j];
                }
            }
            true
        }
        for i in 1..nums.len() {
            if nums[i-1] >= nums[i] {
                return check(&nums, i-1) || check(&nums, i)
            }
        }
        true
    }
}