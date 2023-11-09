impl Solution {
    pub fn smallest_missing_value_subtree(parents: Vec<i32>, nums: Vec<i32>) -> Vec<i32> {
        fn dfs(
            i: usize,
            vis: &mut Vec<bool>,
            has: &mut Vec<bool>,
            g: &Vec<Vec<usize>>,
            nums: &Vec<i32>
        ) {
            if vis[i] {
                return;
            }
            vis[i] = true;
            if nums[i] < (has.len() as i32) {
                has[nums[i] as usize] = true;
            }
            for &j in &g[i] {
                dfs(j, vis, has, g, nums);
            }
        }

        let n = nums.len();
        let mut ans = vec![1; n];
        let mut g: Vec<Vec<usize>> = vec![vec![]; n];
        let mut idx = -1;
        for (i, &p) in parents.iter().enumerate() {
            if i > 0 {
                g[p as usize].push(i);
            }
            if nums[i] == 1 {
                idx = i as i32;
            }
        }
        if idx == -1 {
            return ans;
        }
        let mut vis = vec![false; n];
        let mut has = vec![false; (n + 2) as usize];
        let mut i = 2;
        let mut idx_mut = idx;
        while idx_mut != -1 {
            dfs(idx_mut as usize, &mut vis, &mut has, &g, &nums);
            while has[i] {
                i += 1;
            }
            ans[idx_mut as usize] = i as i32;
            idx_mut = parents[idx_mut as usize];
        }
        ans
    }
}
