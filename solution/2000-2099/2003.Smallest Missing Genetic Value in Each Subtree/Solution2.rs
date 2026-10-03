impl Solution {
    pub fn smallest_missing_value_subtree(parents: Vec<i32>, nums: Vec<i32>) -> Vec<i32> {
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
        let mut has = vec![false; n + 2];
        let mut i = 2;
        while idx != -1 {
            let mut stk = vec![idx as usize];
            while let Some(u) = stk.pop() {
                if vis[u] {
                    continue;
                }
                vis[u] = true;
                if nums[u] < has.len() as i32 {
                    has[nums[u] as usize] = true;
                }
                for &j in &g[u] {
                    stk.push(j);
                }
            }
            while has[i] {
                i += 1;
            }
            ans[idx as usize] = i as i32;
            idx = parents[idx as usize];
        }
        ans
    }
}
