impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if t.len() == k as usize {
            ans.push(t.clone());
            return;
        }
        if i > n {
            return;
        }
        t.push(i);
        Self::dfs(i + 1, n, k, t, ans);
        t.pop();
        Self::dfs(i + 1, n, k, t, ans);
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut ans);
        ans
    }
}
