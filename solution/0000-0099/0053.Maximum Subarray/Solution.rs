impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = nums[0];
        let mut f = nums[0];
        for i in 1..n {
            f = f.max(0) + nums[i];
            ans = ans.max(f);
        }
        ans
    }
}
