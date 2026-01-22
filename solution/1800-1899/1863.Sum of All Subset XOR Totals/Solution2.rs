impl Solution {
    pub fn subset_xor_sum(nums: Vec<i32>) -> i32 {
        fn dfs(i: usize, s: i32, nums: &[i32], ans: &mut i32) {
            if i == nums.len() {
                *ans += s;
                return;
            }
            dfs(i + 1, s, nums, ans);
            dfs(i + 1, s ^ nums[i], nums, ans);
        }

        let mut ans = 0;
        dfs(0, 0, &nums, &mut ans);
        ans
    }
}
