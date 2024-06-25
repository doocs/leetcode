impl Solution {
    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut ans = Vec::new();

        fn dfs(ans: &mut Vec<String>, l: i32, r: i32, t: String, n: i32) {
            if l > n || r > n || l < r {
                return;
            }
            if l == n && r == n {
                ans.push(t);
                return;
            }
            dfs(ans, l + 1, r, format!("{}(", t), n);
            dfs(ans, l, r + 1, format!("{})", t), n);
        }

        dfs(&mut ans, 0, 0, String::new(), n);
        ans
    }
}
