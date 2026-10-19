impl Solution {
    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        fn dfs(open_left: i32, close_left: i32, path: &mut String, ans: &mut Vec<String>) {
            if open_left == 0 && close_left == 0 {
                ans.push(path.clone());
                return;
            }
            if open_left > 0 {
                path.push('(');
                dfs(open_left - 1, close_left, path, ans);
                path.pop();
            }
            if close_left > open_left {
                path.push(')');
                dfs(open_left, close_left - 1, path, ans);
                path.pop();
            }
        }

        let mut ans = Vec::new();
        let mut path = String::with_capacity(n as usize * 2);
        dfs(n, n, &mut path, &mut ans);
        ans
    }
}
