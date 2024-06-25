impl Solution {
    fn dfs(i: &mut usize, expr: &[u8]) -> Vec<bool> {
        let n = expr.len();
        let mut res = Vec::new();
        while *i < n {
            let c = expr[*i];
            *i += 1;
            match c {
                b')' => {
                    break;
                }
                b't' => {
                    res.push(true);
                }
                b'f' => {
                    res.push(false);
                }
                b'!' => {
                    res.push(!Self::dfs(i, expr)[0]);
                }
                b'&' => {
                    res.push(Self::dfs(i, expr).iter().all(|v| *v));
                }
                b'|' => {
                    res.push(Self::dfs(i, expr).iter().any(|v| *v));
                }
                _ => {}
            }
        }
        res
    }

    pub fn parse_bool_expr(expression: String) -> bool {
        let expr = expression.as_bytes();
        let mut i = 0;
        Self::dfs(&mut i, expr)[0]
    }
}
