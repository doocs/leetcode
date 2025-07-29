impl Solution {
    pub fn count_max_or_subsets(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mx = nums.iter().fold(0, |x, &y| x | y);

        fn dfs(i: usize, t: i32, nums: &Vec<i32>, mx: i32, ans: &mut i32) {
            if i == nums.len() {
                if t == mx {
                    *ans += 1;
                }
                return;
            }
            dfs(i + 1, t, nums, mx, ans);
            dfs(i + 1, t | nums[i], nums, mx, ans);
        }

        dfs(0, 0, &nums, mx, &mut ans);
        ans
    }
}
