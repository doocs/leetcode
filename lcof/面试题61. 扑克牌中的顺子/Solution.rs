impl Solution {
    pub fn is_straight(mut nums: Vec<i32>) -> bool {
        nums.sort();
        let mut j = 0;
        for i in 0..4 {
            if nums[i] == 0 {
                j += 1;
            } else if nums[i] == nums[i + 1] {
                return false;
            }
        }
        nums[4] - nums[j] <= 4
    }
}
