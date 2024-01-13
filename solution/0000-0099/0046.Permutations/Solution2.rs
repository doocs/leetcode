impl Solution {
    #[allow(dead_code)]
    pub fn permute(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut ret = Vec::new();
        let mut cur_vec = Vec::new();
        let mut vis = vec![false; nums.len() + 1];
        Self::dfs(&nums, &mut vis, 0, &mut cur_vec, &mut ret);
        ret
    }

    #[allow(dead_code)]
    fn dfs(
        nums: &Vec<i32>,
        vis: &mut Vec<bool>,
        index: i32,
        cur_vec: &mut Vec<i32>,
        ans: &mut Vec<Vec<i32>>
    ) {
        let n = nums.len();
        if (index as usize) == n {
            ans.push(cur_vec.clone());
            return;
        }
        // Otherwise, let's iterate the nums vector
        for i in 0..n {
            if !vis[i] {
                // If this number hasn't been visited, then visit it
                vis[i] = true;
                cur_vec.push(nums[i]);
                Self::dfs(nums, vis, index + 1, cur_vec, ans);
                // Reset the changes
                cur_vec.pop().unwrap();
                vis[i] = false;
            }
        }
    }
}
