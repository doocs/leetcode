impl Solution {
    pub fn find_length_of_lcis(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        let mut cnt = 1;
        for i in 1..nums.len() {
            if nums[i - 1] < nums[i] {
                ans = ans.max(cnt + 1);
                cnt += 1;
            } else {
                cnt = 1;
            }
        }
        ans
    }
}
