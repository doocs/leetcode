impl Solution {
    pub fn predict_the_winner(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut f = vec![vec![0; n]; n];
        Self::dfs(&nums, &mut f, 0, n - 1) >= 0
    }

    fn dfs(nums: &Vec<i32>, f: &mut Vec<Vec<i32>>, i: usize, j: usize) -> i32 {
        if i == j {
            return nums[i] as i32;
        }
        if f[i][j] != 0 {
            return f[i][j];
        }
        f[i][j] = std::cmp::max(
            nums[i] - Self::dfs(nums, f, i + 1, j),
            nums[j] - Self::dfs(nums, f, i, j - 1),
        );
        f[i][j]
    }
}
