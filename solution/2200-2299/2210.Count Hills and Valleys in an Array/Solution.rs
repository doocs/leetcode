impl Solution {
    pub fn count_hill_valley(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut j = 0;

        for i in 1..nums.len() - 1 {
            if nums[i] == nums[i + 1] {
                continue;
            }
            if nums[i] > nums[j] && nums[i] > nums[i + 1] {
                ans += 1;
            }
            if nums[i] < nums[j] && nums[i] < nums[i + 1] {
                ans += 1;
            }
            j = i;
        }

        ans
    }
}
