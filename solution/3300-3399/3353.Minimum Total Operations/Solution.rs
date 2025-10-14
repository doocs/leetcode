impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 1..nums.len() {
            if nums[i] != nums[i - 1] {
                ans += 1;
            }
        }
        ans
    }
}
