impl Solution {
    pub fn permute_unique(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let n = nums.len();
        let mut ans = Vec::new();
        let mut t = vec![0; n];
        let mut vis = vec![false; n];

        fn dfs(
            nums: &Vec<i32>,
            t: &mut Vec<i32>,
            vis: &mut Vec<bool>,
            ans: &mut Vec<Vec<i32>>,
            i: usize,
        ) {
            if i == nums.len() {
                ans.push(t.clone());
                return;
            }
            for j in 0..nums.len() {
                if vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1]) {
                    continue;
                }
                t[i] = nums[j];
                vis[j] = true;
                dfs(nums, t, vis, ans, i + 1);
                vis[j] = false;
            }
        }

        dfs(&nums, &mut t, &mut vis, &mut ans, 0);
        ans
    }
}
