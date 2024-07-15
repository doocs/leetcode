impl Solution {
    pub fn find_value_of_partition(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let mut ans = i32::MAX;
        for i in 1..nums.len() {
            ans = ans.min(nums[i] - nums[i - 1]);
        }
        ans
    }
}
