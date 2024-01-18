impl Solution {
    fn dfs(mut i: usize, s: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if s == 0 {
            ans.push(t.clone());
            return;
        }
        if i >= candidates.len() || s < candidates[i] {
            return;
        }
        let x = candidates[i];
        t.push(x);
        Self::dfs(i + 1, s - x, candidates, t, ans);
        t.pop();
        while i < candidates.len() && candidates[i] == x {
            i += 1;
        }
        Self::dfs(i, s, candidates, t, ans);
    }

    pub fn combination_sum2(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
