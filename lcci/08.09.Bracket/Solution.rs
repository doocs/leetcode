impl Solution {
    fn dfs(left: i32, right: i32, t: String, res: &mut Vec<String>) {
        if left == 0 && right == 0 {
            res.push(t);
            return;
        }
        if left > 0 {
            Self::dfs(left - 1, right, format!("{}(", t), res);
        }
        if left < right {
            Self::dfs(left, right - 1, format!("{})", t), res);
        }
    }

    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut res = vec![];
        Self::dfs(n, n, String::new(), &mut res);
        res
    }
}
