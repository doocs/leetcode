impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        fn dfs(i: usize, nums: &Vec<i32>, f: &mut Vec<i32>) -> i32 {
            if i >= nums.len() {
                return 0;
            }
            if f[i] < 0 {
                f[i] = (nums[i] + dfs(i + 2, nums, f)).max(dfs(i + 1, nums, f));
            }
            f[i]
        }

        let n = nums.len();
        let mut f = vec![-1; n];
        dfs(0, &nums, &mut f)
    }
}
