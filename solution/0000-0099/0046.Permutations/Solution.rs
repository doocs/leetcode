impl Solution {
    pub fn permute(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut ans = Vec::new();
        let mut t = vec![0; n];
        let mut vis = vec![false; n];
        fn dfs(
            nums: &Vec<i32>,
            n: usize,
            t: &mut Vec<i32>,
            vis: &mut Vec<bool>,
            ans: &mut Vec<Vec<i32>>,
            i: usize,
        ) {
            if i == n {
                ans.push(t.clone());
                return;
            }
            for j in 0..n {
                if !vis[j] {
                    vis[j] = true;
                    t[i] = nums[j];
                    dfs(nums, n, t, vis, ans, i + 1);
                    vis[j] = false;
                }
            }
        }
        dfs(&nums, n, &mut t, &mut vis, &mut ans, 0);
        ans
    }
}
